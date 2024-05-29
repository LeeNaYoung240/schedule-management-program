package com.sparta.schedulemanagement.service;


import com.sparta.schedulemanagement.dto.LoginRequestDto;
import com.sparta.schedulemanagement.dto.SignupRequestDto;
import com.sparta.schedulemanagement.entity.User;
import com.sparta.schedulemanagement.entity.UserRoleEnum;
import com.sparta.schedulemanagement.jwt.JwtUtil;
import com.sparta.schedulemanagement.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public void signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        else if (requestDto.getRole() != null) {
            role = requestDto.getRole();
        }

        // 사용자 등록
        User user = new User(username, requestDto.getNickname(), requestDto.getPassword(), role);
        userRepository.save(user);
    }

    public void login(LoginRequestDto requestDto, HttpServletResponse res) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                ()-> new IllegalArgumentException("등록된 사용자가 없습니다.")
        );

        // 비밀번호 확인
        if(password.equals(user.getPassword())) {
            String token = jwtUtil.createToken(user.getUsername(),user.getRole());
            jwtUtil.addJwtToCookie(token, res);
        }
        else{
            throw new IllegalArgumentException("비밀번호를 다시 입력하세요.");
        }
    }
}