package cmc.hackathon.config.auth.dto;

import cmc.hackathon.domain.Member.Member;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;
    private String image;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.image = member.getImage();
    }
}
