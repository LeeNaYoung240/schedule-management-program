package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.CommentModifyRequestDto;
import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.service.CommentService;
import com.sparta.schedulemanagement.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    private final UserService userService;

    @PostMapping("/{scheduleId}")
    public CommentResponseDto createComment(@RequestBody @Valid CommentRequestDto requestDto, @PathVariable Long scheduleId, HttpServletRequest req) {
        return commentService.createComment(requestDto, scheduleId, req);
    }

    @PutMapping("/{commentId}")
    public CommentResponseDto updateComment(@PathVariable Long commentId, @RequestBody @Valid CommentModifyRequestDto requestDto, HttpServletRequest req) {
        return commentService.updateComment(commentId, requestDto, req);
    }

    @DeleteMapping("/{commentId}")
    public String deleteComment(@PathVariable Long commentId, HttpServletRequest req) {
        return commentService.deleteComment(commentId, req);
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> handleEmptyIdRequest() {
        return ResponseEntity.badRequest().body("고유번호를 입력하세요");
    }

}
