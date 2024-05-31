package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ApiResponseDto;
import com.sparta.schedulemanagement.dto.LoginRequestDto;
import com.sparta.schedulemanagement.dto.SignupRequestDto;
import com.sparta.schedulemanagement.service.ScheduleService;
import com.sparta.schedulemanagement.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user/signup")
    public ResponseEntity<ApiResponseDto> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
        ApiResponseDto response = new ApiResponseDto("회원가입 성공🎉", 200);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/user/login")
    public ResponseEntity<ApiResponseDto> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse res) {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        ApiResponseDto response = new ApiResponseDto("로그인 성공🎊", 200);
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDto> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiResponseDto response = new ApiResponseDto(ex.getMessage(), 400);
        return ResponseEntity.badRequest().body(response);
    }
}