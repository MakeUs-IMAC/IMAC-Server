package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.member.CarType;
import cmc.hackathon.domain.member.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAddInfoReq {

    private String nickName;
    private String phone;
    private Gender gender;
    private int age;
    private Role role;
    private CarType carType;
}
