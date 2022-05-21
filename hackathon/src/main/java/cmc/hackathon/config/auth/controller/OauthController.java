package cmc.hackathon.config.auth.controller;

import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.config.auth.dto.OauthResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthController {

    @ApiOperation(value = "로그인 완료후 리다이렉트")
    @GetMapping("/auth")
    public BaseResponse<OauthResponse> jwtResponse(@RequestParam String jwt, @RequestParam Long id) {
        return new BaseResponse<>(new OauthResponse(id,jwt));
    }
}
