package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.post.Post;
import lombok.Data;

@Data
public class GetMemberFavoriteRes {

    private String image;
    private Region region;
    //기간
    private int participants;

    public GetMemberFavoriteRes(Post post) {
        this.image = post.getImage();
        this.region = post.getRegion();
        this.participants = post.getParticipants();
    }
}
