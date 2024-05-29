package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.UserRoleEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequestDto {
    private Long id;
    @NotBlank(message = "닉네임을 입력해 주세요")
    private String nickname;

    @NotBlank(message = "사용자 이름을 입력해 주세요")
    @Size(min = 4, max = 10, message = "최소 4자 이상, 10자 이하로 입력해 주세요")
    @Pattern(regexp = "^[a-z0-9]+$", message = "알파벳 소문자(a~z), 숫자(0~9) 로 입력해 주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해 주세요")
    @Size(min = 8, max = 15, message = "최소 8자 이상, 15자 이하로 입력해 주세요")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "알파벳 대소문자(a~z,A~Z), 숫자(0~9)로 입력해 주세요")
    private String password;

    private boolean admin = false;
    private String adminToken = "";

    private UserRoleEnum role;
}