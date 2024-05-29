package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comment/{scheduleId}")
    public CommentResponseDto createComment(@RequestBody @Valid CommentRequestDto requestDto, @PathVariable(required = false) Long scheduleId) {
        if(scheduleId == null)
        {
            throw new IllegalArgumentException("고유번호를 입력해 주세요.");
        }
        return commentService.createComment(requestDto, scheduleId);
    }

}
