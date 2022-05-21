package cmc.hackathon.domain.review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateReq {
    private Long driverId;
    private int rate;
    private String title;
    private String content;
}
