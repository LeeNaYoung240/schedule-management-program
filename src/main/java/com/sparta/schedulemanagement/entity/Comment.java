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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Comment(CommentRequestDto requestDto, Schedule schedule, User user) {
        this.commentContents = requestDto.getCommentContents();
        this.date = LocalDateTime.now();
        this.schedule = schedule;
        this.user = user;
        this.username = requestDto.getUsername();
    }

    public void update(CommentModifyRequestDto requestDto, User user) {
        this.commentContents = requestDto.getCommentContents();
        this.user = user;
    }
}
