package cmc.hackathon.domain.post;

import cmc.hackathon.domain.address.Address;
import cmc.hackathon.domain.applicants.Applicants;
import cmc.hackathon.domain.companion.Companion;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.member.MemberRepository;
import cmc.hackathon.domain.member.Role;
import cmc.hackathon.domain.place.Place;
import cmc.hackathon.domain.post.dto.GetAllRes;
import cmc.hackathon.domain.post.dto.PlaceDto;
import cmc.hackathon.domain.post.dto.PostCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void save(Long userId, PostCreateReq postCreateReq, String url) {
        //엔티티 조회
        Optional<Member> member = memberRepository.findById(userId);

        //엔티티 생성
        Post post = Post.builder()
                .startDate(postCreateReq.getStart())
                .endDate(postCreateReq.getEnd())
                .content(postCreateReq.getContent())
                .image(url)
                .participants(postCreateReq.getParticipants())
                .driverFlag(postCreateReq.getDriverFlag())
                .build();
        post.changeMember(member.get());

        List<Place> places = postCreateReq.getPlaces().stream().map(p -> Place.create(post))
                        .collect(Collectors.toList());

        int i =0;
        for (Place place : places) {
            place.changeAddresses(postCreateReq.getPlaces().get(i++).getAddressDtos()
                    .stream()
                    .map(a-> Address.create(place, a.getAddress(), a.getName()))
                    .collect(Collectors.toList()));
        }

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

    public Optional<Post> findById(Long postId) {
        return postRepository.findById(postId);
    }

    public void updateStatus(Long postId, TravelStatus travelStatus) {
        Optional<Post> post = postRepository.findById(postId);
        post.get().updateStatus(travelStatus);
    }

    public void updateCompanion(Long postId, Long userId) {
        Optional<Post> post = postRepository.findById(postId);
        Companion companion = Companion.builder()
                .post(post.get())
                .member(memberRepository.findById(userId).get())
                .build();

        post.get().updateCompanions(companion);
    }
}