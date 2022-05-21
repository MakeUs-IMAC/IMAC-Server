package cmc.hackathon.domain.member;

import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.member.dto.EditMemberReq;
import cmc.hackathon.domain.member.dto.PostAddInfoReq;
import cmc.hackathon.domain.post.Post;
import cmc.hackathon.domain.review.Review;
import cmc.hackathon.utils.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    @Transactional
    public void addInfo(PostAddInfoReq postAddInfoReq, Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        member.get().addInfo(postAddInfoReq.getNickName(),postAddInfoReq.getPhone(),postAddInfoReq.getGender(),postAddInfoReq.getAge(), postAddInfoReq.getRole(),postAddInfoReq.getCarType());
    }

    @Transactional
    public void editMember(Long userId, EditMemberReq editMemberReq) throws IOException {
        Optional<Member> member = memberRepository.findById(userId);
        if(editMemberReq.getImage() != null){
            String url = s3Uploader.upload(editMemberReq.getImage(), "member");
            member.get().edit(editMemberReq.getNickName(),url, editMemberReq.getPhone());
        }else {
            member.get().editWithOutImage(editMemberReq.getNickName(), editMemberReq.getPhone());
        }

    }

    @Transactional
    public void deleteMember(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        member.get().delete();
    }

    public List<Favorite> getMemberFavorite(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        return member.get().getFavorites();
    }

    public List<Post> getMemberPosts(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        return member.get().getPosts();
    }

    public List<Review> getMemberReviews(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        return member.get().getReviews();
    }

    public Optional<Member> getMember(Long userId) {
        Optional<Member> member = memberRepository.findById(userId);
        return member;
    }
}
