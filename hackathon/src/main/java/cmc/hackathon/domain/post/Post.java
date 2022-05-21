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
import lombok.NoArgsConstructor;
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

    @Column(length = 20, columnDefinition = "varchar(20) default 'RECRUIT'")
    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Applicants> applicants = new ArrayList<>();

    public Post() {

    }

    public void updateStatus(TravelStatus travelStatus) {
        this.travelStatus = travelStatus;
    }

    @Builder
    public Post(Long id, Region region, String image, String content, int participants, int driverFlag, TravelStatus travelStatus, Member member, LocalDateTime startDate, LocalDateTime endDate, List<Place> places, List<Favorite> favorites, List<Applicants> applicants) {
        this.id = id;
        this.region = region;
        this.image = image;
        this.content = content;
        this.participants = participants;
        this.driverFlag = driverFlag;
        this.travelStatus = travelStatus;
        this.member = member;
        this.startDate = startDate;
        this.endDate = endDate;
        this.places = places;
        this.favorites = favorites;
        this.applicants = applicants;
    }

    public int getFavoriteCount() {
        return favorites.size();
    }

    public void changeMember(Member member) {
        this.member = member;
        member.addPost(this);
    }

    public void addPlace(Place place) {
        places.add(place);

    }
}
