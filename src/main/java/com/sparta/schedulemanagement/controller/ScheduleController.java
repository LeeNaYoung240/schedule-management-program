package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final Map<Long, Schedule> scheduleList = new HashMap<>();

    // 일정 등록
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity (클라이언트 -> 서버)
        Schedule schedule = new Schedule(requestDto);

        // Schedule Max ID Check
        Long maxId = scheduleList.size() > 0 ? Collections.max(scheduleList.keySet()) + 1 : 1;
        schedule.setId(maxId);

        // DB 저장
        scheduleList.put(schedule.getId(), schedule);

        // Entity -> ResponseDto (서버 -> 클라이언트)
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        //비밀번호 설정하여 반환
        scheduleResponseDto.setPassword("-");
        return scheduleResponseDto;
    }
    // 선택 일정 조회
    @GetMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        Schedule schedule = scheduleList.get(scheduleId);
        if(schedule == null) {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
        // 비밀번호를 제외하고 반환
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        responseDto.setPassword("-");

        return responseDto;
    }
}
