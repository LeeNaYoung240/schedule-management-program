package com.sparta.schedulemanagement.controller;

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
    public ResponseEntity<String> signup(@RequestBody @Valid SignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공🎉");
    }

    @PostMapping("/user/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDto requestDto, HttpServletResponse res)
    {
        try {
            userService.login(requestDto, res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok("로그인 성공🎊");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}