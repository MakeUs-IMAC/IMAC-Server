package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.review.Review;
import lombok.Data;

@Data
public class GetMemberReviewsRes {

    private String title;
    private String content;
    private int rate;

    public GetMemberReviewsRes(Review review) {
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rate = review.getRate();
    }
}
