package cmc.hackathon.domain.address;

import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.domain.post.Post;
import cmc.hackathon.domain.post.dto.GetAllRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

@RequiredArgsConstructor
@RestController
public class AddressController {

    private final AddressService addressService;

    @ApiOperation("검색")
    @GetMapping("/search")
    public BaseResponse<List<GetAllRes>> search(@RequestParam String keyword) {
        List<Post> search = addressService.search(keyword);
        List<GetAllRes> getAllRes = search.stream()
                .map(GetAllRes::new)
                .collect(toList());

        return new BaseResponse<>(getAllRes);
    }

}
