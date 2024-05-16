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

        // 이미 삭제된 일정인지 확인
        if(schedule.isDeleted())
        {
            throw new IllegalArgumentException("이미 삭제된 일정입니다. ");
        }
        // 해당 일정 삭제
        schedule.setDeleted(true);

        return responseDto;
    }
    // 전체 일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getAllSchedule() {
        // Map To List
        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>(scheduleList.values().stream()
                .filter(schedule -> !schedule.isDeleted()) // 삭제되지 않은 일정만 필터링
                .map(schedule -> {
                    ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
                    responseDto.setPassword("-");
                    return responseDto;
                })
                .toList());
        // 내림차순 정렬
        Collections.sort(scheduleResponseDtoList, (date1, date2) -> date2.getDate().compareTo(date1.getDate()));

        return scheduleResponseDtoList;
    }
    // 선택 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto, @RequestParam String password) {
        Schedule schedule = scheduleList.get(scheduleId);
        if (schedule == null) {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
        // 비밀번호 체크
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 수정할 정보 업데이트
        schedule.update(requestDto);

        // 비밀번호를 제외하고 반환
        ScheduleResponseDto responseDto = new ScheduleResponseDto(schedule);
        responseDto.setPassword("-");
        return responseDto;
    }
    // 선택 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public Long deleteSchedule(@PathVariable Long scheduleId,  @RequestParam String password) {
        Schedule schedule = scheduleList.get(scheduleId);
        if (schedule == null) {
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
        // 비밀번호 체크
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 이미 삭제된 일정인지 확인
        if(schedule.isDeleted())
        {
            throw new IllegalArgumentException("이미 삭제된 일정입니다. ");
        }
        // 해당 일정 삭제
        schedule.setDeleted(true);
        return scheduleId;
    }
}
