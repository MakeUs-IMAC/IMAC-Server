package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.*;

@Getter
@Setter
public class GetDetailRes {
    private Long postId;

    //지역
    private Region region;

    //이미지
    private String image;

    private String content;

    //기사 모집 여부
    private int driverFlag;

    //기간
    private LocalDateTime start;
    private LocalDateTime end;

    //장소 리스트
    private List<PlaceDto> placeDtos;

    //찜 ~~~
    private int favoriteCount;

    //인원
    private int participants;

    private List<ApplicantsDto> applicantsDtos;

    public GetDetailRes(Post post) {
        this.postId = post.getId();
        this.region = post.getRegion();
        this.image = post.getImage();
        this.content = post.getContent();
        this.driverFlag = post.getDriverFlag();
        this.start = post.getStartDate();
        this.end = post.getEndDate();
        this.placeDtos = post.getPlaces().stream()
                .map(PlaceDto::new)
                .collect(toList());
        this.favoriteCount = post.getFavoriteCount();
        this.participants = post.getParticipants();
        this.applicantsDtos = post.getApplicants().stream()
                .map(ApplicantsDto::new)
                .collect(toList());
    }
}
