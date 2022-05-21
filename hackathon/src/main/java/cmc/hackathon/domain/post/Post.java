package cmc.hackathon.domain.post;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.applicants.Applicants;
import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.place.Place;
import cmc.hackathon.domain.post.dto.GetAllRes;
import cmc.hackathon.domain.post.dto.PostCreateReq;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Post extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "post_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Region region;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(columnDefinition = "TEXT")
    private String content;

    //모집 인원 수
    private int participants;

    //기사 모집 여부 0: false 1: true
    private int driverFlag;

    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime start;

    private LocalDateTime end;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Applicants> applicants = new ArrayList<>();

    @Builder
    public Post(PostCreateReq postCreateReq){
        this.region = postCreateReq.getRegion();
        this.image = postCreateReq.getImage();
        this.content = postCreateReq.getContent();
        this.participants = postCreateReq.getParticipants();
        this.driverFlag = postCreateReq.getDriverFlag();
        this.travelStatus = TravelStatus.RECRUIT;
        this.member = postCreateReq.getMember();
        this.start = postCreateReq.getStart();
        this.end = postCreateReq.getEnd();
        this.places = postCreateReq.getPlaces();
    }
}
