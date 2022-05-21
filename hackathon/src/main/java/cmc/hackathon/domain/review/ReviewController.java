package cmc.hackathon.domain.review;

import cmc.hackathon.config.BaseException;
import cmc.hackathon.config.BaseResponse;

import cmc.hackathon.domain.review.dto.PostCreateReq;
import cmc.hackathon.utils.JwtService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static cmc.hackathon.config.BaseResponseStatus.EMPTY_JWT;
import static cmc.hackathon.config.BaseResponseStatus.INVALID_USER_JWT;


@RestController
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final JwtService jwtService;

    @ApiOperation("후기 작성")
    @PostMapping("/review/{userId}/{postId}")
    public BaseResponse<String> createReview(@PathVariable(name = "userId") Long userId, @PathVariable(name = "postId") Long postId, @RequestBody PostCreateReq postCreateReq){

        try {
            Long userIdByJwt = jwtService.getUserIdx();

            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }

        reviewService.save(userId, postCreateReq);
        return new BaseResponse<>("");
    }


}
