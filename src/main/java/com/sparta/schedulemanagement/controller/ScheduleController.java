package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ApiResponseDto;
import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 등록
    @PostMapping
    public ScheduleResponseDto createSchedule(@RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.createSchedule(requestDto);
    }

    // 선택 일정 조회
    @GetMapping("/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        return scheduleService.getSchedule(scheduleId);
    }

    // 전체 일정 조회
    @GetMapping
    public List<ScheduleResponseDto> getAllSchedule() {
        return scheduleService.getAllSchedule();
    }

    // 선택 일정 수정
    @PutMapping("/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleRequestDto requestDto) {
        return scheduleService.updateSchedule(scheduleId, requestDto, requestDto.getPassword());
    }

    // 선택 일정 삭제
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<ApiResponseDto> deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto) {
        scheduleService.deleteSchedule(scheduleId, requestDto.getPassword());
        ApiResponseDto response = new ApiResponseDto("게시글 삭제 성공🙆🏻‍♀️", 200);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> handleEmptyIdRequest() {
        return ResponseEntity.badRequest().body("고유번호를 입력하세요");
    }
}