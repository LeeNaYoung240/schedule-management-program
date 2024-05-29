package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalArgumentException("["+scheduleId+"]이라는 숫자의 일정이 DB에 저장되어 있지 않습니다. ")
        );

        Comment comment = commentRepository.save(new Comment(requestDto, schedule));
        return new CommentResponseDto(comment);
    }

}
