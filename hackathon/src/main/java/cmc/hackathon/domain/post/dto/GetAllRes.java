package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class GetAllRes {
    private Long id;
    //이미지
    private String image;
    //지역
    private Region region;
    //기간
    private LocalDateTime start;
    private LocalDateTime end;
    //인원
    private int participants;

    @Builder
    public GetAllRes(Post post){
        this.id = post.getId();
        this.image = post.getImage();
        this.region = post.getRegion();
        this.start = post.getStartDate();
        this.end = post.getEndDate();
        this.participants = post.getParticipants();
    }
}
