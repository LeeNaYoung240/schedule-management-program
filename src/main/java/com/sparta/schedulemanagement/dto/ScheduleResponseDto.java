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

    public ScheduleResponseDto(Long id, String title, String contents, String manager, String date, String s) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.manager = manager;
        this.date = date;
    }

}
