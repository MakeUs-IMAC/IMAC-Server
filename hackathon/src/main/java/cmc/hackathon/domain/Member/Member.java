package cmc.hackathon.domain.Member;

import cmc.hackathon.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    private String email;

    public Member() {

    }

    @Builder
    public Member(Long id, String image, String name, String email) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.email = email;
    }

    public Member update(String name, String image) {
        this.name = name;
        this.image = image;

        return this;
    }
}
