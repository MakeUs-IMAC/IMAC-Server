package cmc.hackathon.domain.applicants;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "status='ACTIVE'")
@Getter
@Entity
public class Applicants extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "applicants_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Applicants() {
    }

    @Builder
    public Applicants(Post post, Member member){
        this.post = post;
        this.member = member;
    }
}
