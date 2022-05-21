package cmc.hackathon.domain.alarm.dto;

import cmc.hackathon.domain.alarm.Alarm;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Getter
@Setter
public class GetAllRes {
    private Long id;
    private Long toId;
    private Long fromId;
    private String message;

    @Builder
    public GetAllRes(Alarm alarm){
        this.id = alarm.getId();
        this.toId = alarm.getTo().getId();
        this.fromId = alarm.getFrom().getId();
        this.message = alarm.getMessage();
    }
}
