package cmc.hackathon.domain.member.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class EditMemberReq {

    private String nickName;
    private MultipartFile image;
    private String phone;
}
