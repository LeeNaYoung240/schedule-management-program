package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Getter
@Setter
@Table(name = "schedule")
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = true, length = 500)
    private String title;

    @Column(name = "contents",nullable = true)
    private String contents;

    @Column(name = "manager",nullable = true)
    private String manager;

    @Column(name = "password",nullable = true)
    private String password;

    @Column(name = "date", nullable = true)
    private LocalDate date;

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
        this.password = requestDto.getPassword();
        this.date = requestDto.getDate();
    }

    public void update(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.manager = requestDto.getManager();
    }


}