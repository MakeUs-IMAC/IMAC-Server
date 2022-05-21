package cmc.hackathon.domain.alarm;

import cmc.hackathon.domain.alarm.dto.GetAllRes;
import cmc.hackathon.domain.member.Member;
import cmc.hackathon.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class AlarmService {
    private static AlarmRepository alarmRepository;
    private static MemberRepository memberRepository;

    public List<GetAllRes> findAllAlarms(Long userId) {
        List<Alarm> alarms = alarmRepository.findAllById(Collections.singleton(userId));
        List<GetAllRes> getAllRes = alarms.stream().map(GetAllRes::new)
                .collect(Collectors.toList());
        return getAllRes;
    }

    @Transactional
    public void save(Long fromId, Long toId, String message){
        Optional<Member> from = memberRepository.findById(fromId);
        Optional<Member> to = memberRepository.findById(toId);

        Alarm alarm = Alarm.builder()
                .from(from.get())
                .to(to.get())
                .message(message)
                .build();

        alarmRepository.save(alarm);
    }
}
