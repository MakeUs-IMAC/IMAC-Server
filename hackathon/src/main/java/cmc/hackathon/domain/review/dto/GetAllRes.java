package cmc.hackathon.domain.review.dto;

import cmc.hackathon.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllRes {
    private Long id;
    private String title;
    private int rate;
    private String content;

    @Builder
    public GetAllRes(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.rate = review.getRate();
        this.content = review.getContent();
    }
}
