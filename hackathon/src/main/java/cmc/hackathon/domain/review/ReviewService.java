package cmc.hackathon.domain.review;

import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.member.MemberRepository;
import cmc.hackathon.domain.review.dto.GetAllRes;
import cmc.hackathon.domain.review.dto.PostCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {
    private static ReviewRepository reviewRepository;
    private static MemberRepository memberRepository;

    @Transactional
    public void save(Long userId, PostCreateReq postCreateReq) {
        Optional<Member> writer = memberRepository.findById(userId);
        Optional<Member> driver = memberRepository.findById(postCreateReq.getDriverId());

        Review review = Review.builder()
                .driver(driver.get())
                .writer(writer.get())
                .createReq(postCreateReq)
                .build();

        driver.get().updateReview(review);
    }

    public List<GetAllRes> findReviews(Long userId) {
        Optional<Member> driver = memberRepository.findById(userId);
        List<Review> reviews = reviewRepository.findByDriver(driver.get());
        List<GetAllRes> getAllRes = reviews.stream().map(GetAllRes::new)
                .collect(Collectors.toList());
        return getAllRes;
    }
}
