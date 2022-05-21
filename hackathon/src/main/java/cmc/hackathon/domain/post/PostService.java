package cmc.hackathon.domain.post;

import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.member.MemberRepository;
import cmc.hackathon.domain.member.Role;
import cmc.hackathon.domain.place.dto.PlaceResponseDto;
import cmc.hackathon.domain.post.dto.GetAllRes;
import cmc.hackathon.domain.post.dto.GetDetailRes;
import cmc.hackathon.domain.post.dto.PostCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void save(Long userId, PostCreateReq postCreateReq, String url) {
        Optional<Member> member = memberRepository.findById(userId);
        postCreateReq.setMember(member.get());
        postCreateReq.setImage(url);
        postCreateReq.toEntity();
    }

    public List<GetAllRes> findPosts(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);

        if (member.get().getRole() == Role.DRIVER) {
            List<Post> posts = postRepository.findAllByDriverFlag(1);
            List<GetAllRes> getAllRes = posts.stream()
                    .map(GetAllRes::new)
                    .collect(Collectors.toList());
            return getAllRes;
        }

        List<Post> posts = postRepository.findAll();
        List<GetAllRes> getAllRes = posts.stream()
                .map(GetAllRes::new)
                .collect(Collectors.toList());

        return getAllRes;
    }

//
//    public GetDetailRes findOne(Long postId) {
//        Optional<Post> post = postRepository.findById(postId);
//        List<PlaceResponseDto> placeResponseDtos = post.get().getPlaces().stream().map(p -> p)
//                .collect(Collectors.toList());
//    }
}