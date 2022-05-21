package cmc.hackathon.domain.post.dto;

import cmc.hackathon.domain.Gender;
import cmc.hackathon.domain.applicants.Applicants;
import cmc.hackathon.domain.member.CarType;
import cmc.hackathon.domain.member.Role;
import lombok.Data;

@Data
public class ApplicantsDto {

    private Long memberId;
    private Role role;
    private String nickName;

    private CarType carType;
    private int rate;

    private Gender gender;

    private int age;

    public ApplicantsDto(Applicants applicants) {
        this.memberId = applicants.getMember().getId();
        this.role = applicants.getMember().getRole();
        this.nickName = applicants.getMember().getNickname();
        this.carType = applicants.getMember().getCarType();
        this.rate = applicants.getMember().getAverageRate();
        this.gender = applicants.getMember().getGender();
        this.age = applicants.getMember().getAge();
    }
}
