package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.favorite.dto.FavoriteResponseDto;
import cmc.hackathon.domain.place.Place;
import cmc.hackathon.domain.place.dto.PlaceResponseDto;
import cmc.hackathon.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class GetDetailRes {
    private Long id;

    //지역
    private Region region;

    //이미지
    private String image;

    private String content;

    //기사 모집 여부
    private int driverFlag;

    private Long memberId;

    //기간
    private LocalDateTime start;
    private LocalDateTime end;

    //장소 리스트
    private List<PlaceResponseDto> placeResponseDtos;

    //찜 ~~~
    private List<FavoriteResponseDto> favoriteResponseDtos;

    //인원
    private int participants;

    @Builder
    public GetDetailRes(Post post, List<PlaceResponseDto> placeResponseDto, List<FavoriteResponseDto> favoriteResponseDtos){
        this.id = post.getId();
        this.region = post.getRegion();
        this.image = post.getImage();
        this.driverFlag = post.getDriverFlag();
        this.memberId = post.getMember().getId();
        this.start = post.getStart();
        this.end = post.getEnd();
        this.placeResponseDtos = placeResponseDto;
        this.favoriteResponseDtos = favoriteResponseDtos;
        this.participants = post.getParticipants();
    }
}
