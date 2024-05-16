package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
/**
 * 일정의 id, 제목, 내용, 담당자, 비밀번호, 작성일, id는 자동 부여
 */
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String manager;
    private String password;
    private String date;

    // 생성자를 통한 객체 생성
    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    // 객체의 정보 업데이트
    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }
}
