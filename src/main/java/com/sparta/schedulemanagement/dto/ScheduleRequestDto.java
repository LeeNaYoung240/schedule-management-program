package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 사용자가 등록할 제목, 내용, 담당자, 비밀번호, 작성일
 *
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequestDto {
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;
    @NotBlank(message = "내용을 입력해 주세요")
    private String contents;
    @NotBlank(message = "관리자를 입력해 주세요")
    private String manager;
    @NotBlank(message = "비밀번호를 입력해 주세요")
    private String password;
}
