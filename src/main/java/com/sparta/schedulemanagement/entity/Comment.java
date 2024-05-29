package com.sparta.schedulemanagement.entity;

import com.sparta.schedulemanagement.dto.CommentModifyRequestDto;
import com.sparta.schedulemanagement.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "commentContents",nullable = false)
    private String commentContents;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

    public Comment(CommentRequestDto requestDto, Schedule schedule) {
        this.commentContents = requestDto.getCommentContents();
        this.username = requestDto.getUsername();
        this.date = LocalDateTime.now();
        this.schedule = schedule;
    }

    public void update(CommentModifyRequestDto requestDto) {
        this.commentContents = requestDto.getCommentContents();
    }
}
