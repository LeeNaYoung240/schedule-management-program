package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
