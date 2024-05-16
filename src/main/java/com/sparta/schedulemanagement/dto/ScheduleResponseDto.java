package com.sparta.schedulemanagement.dto;

import com.sparta.schedulemanagement.entity.Schedule;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate date;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.manager = schedule.getManager();
        this.date = schedule.getDate();
    }

}
