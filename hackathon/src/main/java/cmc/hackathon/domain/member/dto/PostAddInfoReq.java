package cmc.hackathon.domain.member.dto;

import cmc.hackathon.domain.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostAddInfoReq {

    private String nickName;
    private String phone;
    private Gender gender;
    private int age;
}
