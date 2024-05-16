package com.sparta.schedulemanagement.controller;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    private final JdbcTemplate jdbcTemplate;

    public ScheduleController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 등록
    @PostMapping("/schedule")
    public ScheduleResponseDto createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (title, contents, manager, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, requestDto.getTitle());
            preparedStatement.setString(2, requestDto.getContents());
            preparedStatement.setString(3, requestDto.getManager());
            preparedStatement.setString(4, requestDto.getPassword());
            preparedStatement.setString(5, requestDto.getDate());
            return preparedStatement;
        }, keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        // Entity -> ResponseDto
        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);
        return scheduleResponseDto;
    }

    // 선택 일정 조회
    @GetMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto getSchedule(@PathVariable Long scheduleId) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scheduleId}, new ScheduleRowMapper());
    }

    // 전체 일정 조회
    @GetMapping("/schedule")
    public List<ScheduleResponseDto> getAllSchedule() {
        String sql = "SELECT * FROM schedule WHERE is_deleted = false ORDER BY date DESC";
        return jdbcTemplate.query(sql, new ScheduleRowMapper());
    }

    // 선택 일정 수정
    @PutMapping("/schedule/{scheduleId}")
    public ScheduleResponseDto updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleRequestDto requestDto, @RequestParam String password) {
        // 해당 스케줄이 DB에 존재하는지 확인
        String getPasswordSql = "SELECT password FROM schedule WHERE id = ?";
        String savedPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, scheduleId);

        if (!password.equals(savedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String sql = "UPDATE schedule SET title = ?, contents = ?, manager = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getDate(), scheduleId);

        return new ScheduleResponseDto(scheduleId, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getDate(), "-");
    }

    // 선택 일정 삭제
    @DeleteMapping("/schedule/{scheduleId}")
    public Long deleteSchedule(@PathVariable Long scheduleId, @RequestParam String password) {
        String getPasswordSql = "SELECT password FROM schedule WHERE id = ?";
        String savedPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, scheduleId);

        if (!password.equals(savedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, scheduleId);

        return scheduleId;
    }

    private static class ScheduleRowMapper implements RowMapper<ScheduleResponseDto> {
        @Override
        public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String title = rs.getString("title");
            String contents = rs.getString("contents");
            String manager = rs.getString("manager");
            String date = rs.getString("date");
            return new ScheduleResponseDto(id, title, contents, manager, date, "-");
        }
    }
}