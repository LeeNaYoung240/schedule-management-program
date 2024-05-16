package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
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
        Schedule schedule = findSchedule(scheduleId);
        return new ScheduleResponseDto(schedule);
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> getAllSchedule() {
        // DB 조회
        return scheduleRepository.findAllByOrderByDateDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    @Transactional
    // 선택 일정 수정
    public ScheduleResponseDto updateSchedule(Long scheduleId, ScheduleRequestDto requestDto, String password) {
        Schedule schedule = findSchedule(scheduleId);
        // 일정 내용 수정
        if(schedule.getPassword().equals(password)) {
            schedule.update(requestDto);
        }
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }
    @Transactional
    //선택 일정 삭제
    public Long deleteSchedule(Long scheduleId, String password) {
        Schedule schedule = findSchedule(scheduleId);
        if(schedule.getPassword().equals(password)) {
            scheduleRepository.delete(schedule);
        }
        else{
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return scheduleId;
    }

    private Schedule findSchedule(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).orElseThrow(()->
                new IllegalArgumentException("선택한 일정은 존재하지 않습니다.")
        );
    }
}
