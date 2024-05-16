package com.sparta.schedulemanagement.dto;

import lombok.Getter;

/**
 * 사용자가 등록할 제목, 내용, 담당자, 비밀번호, 작성일
 *
 */
@Getter
public class ScheduleRequestDto {
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;
}
