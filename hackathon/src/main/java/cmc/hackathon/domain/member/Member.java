package cmc.hackathon.domain.member;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.Status;
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

    public Member addInfo(String nickName, String phone, Gender gender, int age) {
        this.nickname = nickName;
        this.phone = phone;
        this.gender = gender;
        this.age = age;
        return this;
    }

    public void edit(String nickname, String image, String phone){
        this.nickname = nickname;
        this.image = image;
        this.phone = phone;
    }

    public void editWithOutImage(String nickName, String phone) {
        this.nickname = nickname;
        this.phone = phone;
    }

    public void delete() {
        changeStatus(Status.DELETED);
    }

    public int getAverageRate() {
        int sum = 0;
        for (Review review : reviews) {
            sum += review.getRate();
        }
        return sum/ reviews.size();
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
