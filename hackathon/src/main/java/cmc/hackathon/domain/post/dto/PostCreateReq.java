package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.place.Place;
import cmc.hackathon.domain.post.Post;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostCreateReq {
    private Member member;
    private String image;
    private Region region;
    private String content;
    private int participants;
    private int driverFlag;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<Place> places;

    public Post toEntity(){
        return Post.builder()
                .postCreateReq(this)
                .build();
    }
}
