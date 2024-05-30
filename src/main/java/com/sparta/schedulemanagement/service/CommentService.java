package com.sparta.schedulemanagement.service;

import com.sparta.schedulemanagement.dto.CommentModifyRequestDto;
import com.sparta.schedulemanagement.dto.CommentRequestDto;
import com.sparta.schedulemanagement.dto.CommentResponseDto;

import com.sparta.schedulemanagement.entity.Comment;
import com.sparta.schedulemanagement.entity.Schedule;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.jwt.JwtUtil;
import com.sparta.schedulemanagement.repository.CommentRepository;
import com.sparta.schedulemanagement.repository.ScheduleRepository;
import com.sparta.schedulemanagement.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public CommentResponseDto createComment(CommentRequestDto requestDto, long scheduleId, HttpServletRequest req) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(()
                -> new IllegalArgumentException("[" + scheduleId + "]이라는 숫자의 일정이 DB에 저장되어 있지 않습니다. ")
        );

        // JWT 토큰에서 사용자 정보 추출
        String token = jwtUtil.getJwtFromHeader(req);
        System.out.println(token);

        Claims userClaims = jwtUtil.getUserInfoFromToken(token);

        String username = userClaims.getSubject();
        System.out.println(username);
        // 사용자 정보 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));

        // 댓글 등록
        Comment comment = commentRepository.save(new Comment(requestDto, schedule, user));

        return new CommentResponseDto(comment);
    }


    @Transactional
    public CommentResponseDto updateComment(Long commentId, CommentModifyRequestDto requestDto, HttpServletRequest req) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("[" + commentId + "]이라는 숫자의 댓글이 선택한 일정에 속하지 않습니다."));

        // JWT 토큰에서 사용자 정보 추출
        String token = jwtUtil.getJwtFromHeader(req);
        System.out.println(token);

        Claims userClaims = jwtUtil.getUserInfoFromToken(token);

        String username = userClaims.getSubject();
        System.out.println(username);

        // 사용자 정보 조회
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다."));


        comment.update(requestDto, user);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public String deleteComment(Long commentId, HttpServletRequest req) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() ->
                new IllegalArgumentException("[" + commentId + "]이라는 숫자의 댓글이 선택한 일정에 속하지 않습니다."));

        commentRepository.delete(comment);

        return "[" + commentId + "] 번호가 삭제되었습니다";
    }
}
