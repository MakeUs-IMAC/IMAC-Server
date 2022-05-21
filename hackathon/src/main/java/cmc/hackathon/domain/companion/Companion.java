package cmc.hackathon.domain.companion;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "status='ACTIVE'")
@Entity
@Getter
public class Companion extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companion_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public Companion() {
    }

    @Builder
    public Companion(Post post, Member member){
        this.post = post;
        this.member = member;
    }
}
