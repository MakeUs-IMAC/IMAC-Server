package cmc.hackathon.domain.alarm;

import cmc.hackathon.domain.BaseEntity;
import cmc.hackathon.domain.member.Member;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Where(clause = "status='ACTIVE'")
@Entity
@Getter
public class Alarm extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Member to;

    private String message;

    @Builder
    public Alarm(Member from, Member to, String message){
        this.from = from;
        this.to = to;
        this.message = message;
    }

    public Alarm() {
    }
}
