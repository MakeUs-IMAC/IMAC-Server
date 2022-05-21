package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.member.Member;
import lombok.Data;

@Data
public class GetMemberRes {

    private String image;
    private String nickName;
    private Gender gender;
    private String phone;

    public GetMemberRes(Member member) {
        this.image = member.getImage();
        this.nickName = member.getNickname();
        this.gender = member.getGender();
        this.phone = member.getPhone();
    }
}
