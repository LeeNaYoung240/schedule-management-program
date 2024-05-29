package com.sparta.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Getter
@Table(name = "comments")
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "comment_contents",nullable = false)
    private String comment_contents;

    @Column(name = "user_id",nullable = false)
    private String user_id;

    @Column(name = "date", nullable = false)
    private LocalDate date = LocalDate.now();

    @ManyToOne
    @JoinColumn(name = "schedule_id", nullable = false)
    private Schedule schedule;

}
