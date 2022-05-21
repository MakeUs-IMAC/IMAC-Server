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
        System.out.println(member.get().getId()+"??????????????????????????????????????????????");
        System.out.println(sessionMember.getId()+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println(jwt);

        String targetUrl = UriComponentsBuilder.fromUriString("/auth")
                .queryParam("jwt",jwt)
                .queryParam("id", member.get().getId())
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request,response,targetUrl);

    }

}
