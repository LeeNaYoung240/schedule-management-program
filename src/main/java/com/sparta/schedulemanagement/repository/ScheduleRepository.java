package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByOrderByDateDesc();
}
