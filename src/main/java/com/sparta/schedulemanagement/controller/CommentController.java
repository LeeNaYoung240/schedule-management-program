package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentModifyRequestDto;
import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{scheduleId}")
    public CommentResponseDto createComment(@RequestBody @Valid CommentRequestDto requestDto, @PathVariable(required = false) Long scheduleId) {
        return commentService.createComment(requestDto, scheduleId);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentModifyRequestDto requestDto) {
        return commentService.updateComment(commentId, requestDto);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<String> handleEmptyIdRequest() {
        return ResponseEntity.badRequest().body("고유번호를 입력하세요");
    }

}
