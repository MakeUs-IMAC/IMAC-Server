package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.member.CarType;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.member.Role;
import lombok.Data;

@Data
public class GetMemberRes {

    private String image;
    private String nickName;
    private Gender gender;
    private String phone;
    private Role role;
    private CarType carType;

    public GetMemberRes(Member member) {
        this.image = member.getImage();
        this.nickName = member.getNickname();
        this.gender = member.getGender();
        this.phone = member.getPhone();
        this.role = member.getRole();
        this.carType = member.getCarType()
    }
}
