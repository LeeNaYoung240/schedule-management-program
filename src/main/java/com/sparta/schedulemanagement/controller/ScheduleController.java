package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    // 일정 등록
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 선택 일정 조회
    @GetMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    // 전체 일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    // 선택 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto, requestDto.getPassword());
    }

    // 선택 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public Long deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        return scheduleService.deleteSchedule(scheduleId, requestDto.getPassword());
    }
}