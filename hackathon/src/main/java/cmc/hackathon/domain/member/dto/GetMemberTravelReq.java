package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.post.Post;
import cmc.hackathon.domain.post.TravelStatus;
import lombok.Data;

@Data
public class GetMemberTravelReq {

    private Long postId;
    private Region region;
    private TravelStatus status;

    public GetMemberTravelReq(Post post) {
        this.postId = post.getId();
        this.region = post.getRegion();
        this.status = post.getTravelStatus();
    }
}
