package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ApiResponseDto;
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
    public ResponseEntity<ApiResponseDto> deleteComment(@PathVariable Long commentId, HttpServletRequest req) {
        try {
            commentService.deleteComment(commentId, req);
            ApiResponseDto response = new ApiResponseDto("댓글 삭제 성공🎉", 200);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponseDto response = new ApiResponseDto(e.getMessage(), 400);
            return ResponseEntity.badRequest().body(response);
        } catch (Exception e) {
            ApiResponseDto response = new ApiResponseDto("서버 오류가 발생했습니다.😓", 500);
            return ResponseEntity.status(500).body(response);
        }
    }

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<String> handleEmptyIdRequest() {
        return ResponseEntity.badRequest().body("고유번호를 입력하세요.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto> handleException(Exception ex) {
        ApiResponseDto response = new ApiResponseDto("서버 오류가 발생했습니다: " + ex.getMessage(), 500);
        return ResponseEntity.status(500).body(response);
    }

}
