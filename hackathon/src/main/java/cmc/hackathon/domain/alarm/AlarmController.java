package cmc.hackathon.domain.alarm;

import cmc.hackathon.config.BaseResponse;
import cmc.hackathon.domain.alarm.dto.GetAllRes;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class AlarmController {
    private static AlarmService alarmService;

    @ApiOperation("알람 확인")
    @GetMapping("/alarm/{userId}")
    public BaseResponse<List<GetAllRes>> getAlarms(@PathVariable(name = "userId") Long userId){
        return new BaseResponse<List<GetAllRes>>(alarmService.findAllAlarms(userId));
    }

}
