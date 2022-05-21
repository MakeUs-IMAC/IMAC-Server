package cmc.hackathon.domain.member;

import cmc.hackathon.config.BaseException;
import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.domain.favorite.Favorite;
import cmc.hackathon.domain.member.dto.*;
import cmc.hackathon.domain.post.Post;
import cmc.hackathon.domain.review.Review;
import cmc.hackathon.utils.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static cmc.hackathon.config.BaseResponseStatus.*;
import static java.util.stream.Collectors.*;

@RequestMapping("/member")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final JwtService jwtService;

    @ApiOperation("개인정보입력")
    @PostMapping("/{userId}")
    public BaseResponse<String> addInfo(@RequestBody PostAddInfoReq postAddInfoReq, @PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            memberService.addInfo(postAddInfoReq, userId);

            return new BaseResponse<>("");
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }

    @ApiOperation("프로필 조회")
    @GetMapping("/{userId}")
    public BaseResponse<GetMemberRes> getMember(@PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            Optional<Member> member = memberService.getMember(userId);
            GetMemberRes getMemberRes = new GetMemberRes(member.get());

            return new BaseResponse<>(getMemberRes);
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }

    @ApiOperation("프로필 수정")
    @PatchMapping("/{userId}")
    public BaseResponse<String> editMember(@PathVariable Long userId, @RequestBody EditMemberReq editMemberReq) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            memberService.editMember(userId, editMemberReq);

            return new BaseResponse<>("");
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        } catch (Exception e) {
            return new BaseResponse<>(REQUEST_ERROR);
        }
    }

    @ApiOperation("회원 탈퇴")
    @PatchMapping("/status/{userId}")
    public BaseResponse<String> deleteMember(@PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            memberService.deleteMember(userId);

            return new BaseResponse<>("");
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }


    @ApiOperation("내 관심목록")
    @GetMapping("/favorites/{userId}")
    public BaseResponse<List<GetMemberFavoriteRes>> getMemberFavorite(@PathVariable Long userId) {

        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<Favorite> memberFavorite = memberService.getMemberFavorite(userId);
            List<GetMemberFavoriteRes> getMemberFavoriteRes = memberFavorite.stream()
                    .map(f -> new GetMemberFavoriteRes(f.getPost()))
                    .collect(toList());

            return new BaseResponse<>(getMemberFavoriteRes);
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }

    @ApiOperation("내가 쓴 글 목록")
    @GetMapping("/posts/{userId}")
    public BaseResponse<List<GetMemberFavoriteRes>> getMemberPosts(@PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<Post> memberPosts = memberService.getMemberPosts(userId);
            List<GetMemberFavoriteRes> getMemberFavoriteRes = memberPosts.stream()
                    .map(GetMemberFavoriteRes::new)
                    .collect(toList());

            return new BaseResponse<>(getMemberFavoriteRes);
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }

    @ApiOperation("후기 목록(기사님 전용)")
    @GetMapping("/reviews/{userId}")
    public BaseResponse<List<GetMemberReviewsRes>> getMemberReviews(@PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<Review> memberReviews = memberService.getMemberReviews(userId);
            List<GetMemberReviewsRes> getMemberReviewsRes = memberReviews.stream()
                    .map(GetMemberReviewsRes::new)
                    .collect(toList());

            return new BaseResponse<>(getMemberReviewsRes);
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }

    @ApiOperation("여행 관리")
    @GetMapping("/travel/{userId}")
    public BaseResponse<List<GetMemberTravelReq>> getMemberTravel(@PathVariable Long userId) {
        try {
            Long userIdByJwt = jwtService.getUserIdx();
            if (!Objects.equals(userId, userIdByJwt)) {
                return new BaseResponse<>(INVALID_USER_JWT);
            }

            List<Post> memberTravel = memberService.getMemberTravel(userId);
            List<GetMemberTravelReq> getMemberTravelReqs = memberTravel.stream()
                    .map(GetMemberTravelReq::new)
                    .collect(toList());

            return new BaseResponse<>(getMemberTravelReqs);
        } catch (BaseException e) {
            return new BaseResponse<>(EMPTY_JWT);
        }
    }
}
