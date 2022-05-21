package cmc.hackathon.domain.post;

import cmc.hackathon.domain.Region;
import cmc.hackathon.domain.applicants.Applicants;
import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.place.Place;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Post {

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

    private int participants;

    private int driverFlag;

    @Enumerated(EnumType.STRING)
    private TravelStatus travelStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Place> places = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Applicants> applicants = new ArrayList<>();
}
