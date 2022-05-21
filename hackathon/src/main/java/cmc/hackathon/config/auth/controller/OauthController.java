package cmc.hackathon.config.auth.controller;

import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.config.auth.dto.OauthResponse;
import cmc.hackathon.utils.S3Uploader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class OauthController {

    private final S3Uploader s3Uploader;

    @ApiOperation(value = "로그인 완료후 리다이렉트")
    @GetMapping("/auth")
    public BaseResponse<OauthResponse> jwtResponse(@RequestParam String jwt, @RequestParam Long id) {
        return new BaseResponse<>(new OauthResponse(id,jwt));
    }

    @PostMapping("/test")
    public String test(@RequestParam("images") MultipartFile multipartFile) throws IOException {
        s3Uploader.upload(multipartFile, "test");
        return "";
    }
}
