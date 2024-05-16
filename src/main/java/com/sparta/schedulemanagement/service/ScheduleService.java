package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(JdbcTemplate jdbcTemplate) {
        this.scheduleRepository = new ScheduleRepository(jdbcTemplate);
    }

    // 일정 등록
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);
        // DB 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);
        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(saveSchedule);
        return scheduleResponseDto;
    }

    // 선택 일정 조회
    public ScheduleResponseDto getSchedule(Long scheduleId) {
        //DB 조회
        return scheduleRepository.findSelect(scheduleId);
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getAllSchedule() {
        // DB 조회
        return scheduleRepository.findAll();
    }

    // 선택 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto, String password) {
        if(scheduleId!=null) {
            // 일정 내용 수정
            scheduleRepository.update(scheduleId,requestDto,password);
            return new ScheduleResponseDto(scheduleId, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getDate(), "-");
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }

    //선택 일정 삭제
    public Long deleteSchedule(Long scheduleId, String password) {
        if(scheduleId!=null) {
            scheduleRepository.delete(scheduleId, password);
            return scheduleId;
        }
        else{
            throw new IllegalArgumentException("선택한 일정은 존재하지 않습니다.");
        }
    }
}
