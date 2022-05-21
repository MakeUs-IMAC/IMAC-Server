package cmc.hackathon.domain.review;

import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.review.dto.PostCreateReq;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Review {

    @Id
    @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member driver;

    private int rate;

    @Column(columnDefinition = "TEXT")
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Builder
    public Review(PostCreateReq createReq, Member writer, Member driver){
        this.writer = writer;
        this.driver = driver;
        this.rate = createReq.getRate();
        this.title = createReq.getTitle();
        this.content = createReq.getContent();
    }
}
