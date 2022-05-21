package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostCreateReq {
    private Region region;
    private String content;
    private int participants;
    private int driverFlag;
    private LocalDateTime start;
    private LocalDateTime end;
    private List<PlaceDto> places;

}
