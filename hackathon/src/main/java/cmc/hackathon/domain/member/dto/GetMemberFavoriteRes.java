package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.post.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetMemberFavoriteRes {

    private String image;
    private Region region;
    private LocalDateTime start;
    private LocalDateTime end;
    private int participants;

    public GetMemberFavoriteRes(Post post) {
        this.image = post.getImage();
        this.region = post.getRegion();
        this.start = post.getStartDate();
        this.end = post.getEndDate();
        this.participants = post.getParticipants();
    }
}
