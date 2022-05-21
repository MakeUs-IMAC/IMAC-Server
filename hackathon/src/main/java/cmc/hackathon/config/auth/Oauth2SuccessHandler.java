package cmc.hackathon.config.auth;

import cmc.hackathon.config.auth.dto.SessionMember;
import cmc.hackathon.domain.Member.Member;
import cmc.hackathon.domain.Member.MemberRepository;
import cmc.hackathon.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class Oauth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        HttpSession session = request.getSession();
        SessionMember sessionMember = (SessionMember) session.getAttribute("member");
        Optional<Member> member = memberRepository.findByEmail(sessionMember.getEmail());

        String jwt = jwtService.createJwt(member.get().getId());

        writeTokenResponse(response, jwt);
        String targetUrl = UriComponentsBuilder.fromUriString("/auth")
                .queryParam("jwt",jwt)
                .queryParam("id", member.get().getId())
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request,response,targetUrl);

    }

    private void writeTokenResponse(HttpServletResponse response, String jwt) {
        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Jwt", jwt);
        response.setContentType("application/json;charset=UTF-8");

        try {
            PrintWriter writer = response.getWriter();
            writer.println(jwt);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
