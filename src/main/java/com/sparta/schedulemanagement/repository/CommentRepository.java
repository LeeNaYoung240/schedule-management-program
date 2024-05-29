package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long > {
    Optional<Comment> findByIdAndScheduleId(Long commentId, Long scheduleId);
}

