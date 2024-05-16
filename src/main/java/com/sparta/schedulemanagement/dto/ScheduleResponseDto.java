package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/*
 *   사용자에게 반환할 id, 제목, 내용, 담당자, 작성일
 * */
public class ScheduleResponseDto {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.password = schedule.getPassword();
        this.date = schedule.getDate();
    }
    // 비밀번호 설정
    public void setPassword(Object password) {
        this.password = (String) password;
    }
}
