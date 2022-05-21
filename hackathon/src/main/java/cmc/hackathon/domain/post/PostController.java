package cmc.hackathon.domain.post;

import cmc.hackathon.config.BaseException;
import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.domain.post.dto.GetAllRes;
import cmc.hackathon.domain.post.dto.GetDetailRes;
import cmc.hackathon.domain.post.dto.PostCreateReq;
import cmc.hackathon.utils.JwtService;
import cmc.hackathon.utils.S3Uploader;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static cmc.hackathon.config.BaseResponseStatus.EMPTY_JWT;
import static cmc.hackathon.config.BaseResponseStatus.INVALID_USER_JWT;

@RequestMapping("/post")
@RestController
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final JwtService jwtService;
    private final S3Uploader s3Uploader;

    @ApiOperation("여행 모집 글 쓰긩")
    @PostMapping("/new")
    public BaseResponse<String> PostCreate(@RequestBody Long userId, PostCreateReq postCreateReq, @RequestPart(required = false) MultipartFile image) throws IOException {

        try {
            Long userIdByJwt = jwtService.getUserIdx();

            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }

        String url = s3Uploader.upload(image, "post");

        postService.save(userId, postCreateReq, url);
        return new BaseResponse<String>("");
    }

    @ApiOperation("여행 글 전체 조회")
    @GetMapping("/")
    public BaseResponse<List<GetAllRes>> PostingList(Long userId){
        return new BaseResponse<>(postService.findPosts(userId));
    }

//    @ApiOperation("글 상세 조회")
//    @GetMapping("/{postId}")
//    public BaseResponse<GetDetailRes> Posting(@PathVariable("postId") Long postId){
//        return new BaseResponse<GetDetailRes>(postService.findOne(postId));
//    }

    @ApiOperation("상태 수정")
    @PatchMapping("/{postId}")
    public BaseResponse<String> updatePostStatus(@PathVariable("postId") Long postId, @RequestBody TravelStatus travelStatus){
        postService.updateStatus(postId, travelStatus);
        return new BaseResponse<String>("");
    }
}
