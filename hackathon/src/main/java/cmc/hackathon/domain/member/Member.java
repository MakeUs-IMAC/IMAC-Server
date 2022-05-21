package cmc.hackathon.domain.member;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.applicants.Applicants;
import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.post.Post;
import cmc.hackathon.domain.review.Review;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column(length = 20)
    private String name;

    @Column(length = 20)
    private String nickname;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Role role;

    private int age;

    private String email;

    @Enumerated(EnumType.STRING)
    private CarType carType;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Applicants> applicants = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();


    public Member() {

    }

    @Builder
    public Member(Long id, String image, String name, String email) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.email = email;
    }

    public Member update(String name) {
        this.name = name;
        return this;
    }
}
