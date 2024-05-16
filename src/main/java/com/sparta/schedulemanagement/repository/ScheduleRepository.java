package com.sparta.schedulemanagement.repository;

import com.sparta.schedulemanagement.dto.ScheduleRequestDto;
import com.sparta.schedulemanagement.dto.ScheduleResponseDto;
import com.sparta.schedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // 일정 등록
    public Schedule save(Schedule schedule) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder();

        String sql = "INSERT INTO schedule (title, contents, manager, password, date) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, schedule.getTitle());
            preparedStatement.setString(2, schedule.getContents());
            preparedStatement.setString(3, schedule.getManager());
            preparedStatement.setString(4, schedule.getPassword());
            preparedStatement.setString(5, schedule.getDate());
            return preparedStatement;
        }, keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);

        return schedule;
    }

    // 전체 일정 조회
    public List<ScheduleResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE is_deleted = false ORDER BY date DESC";
        return jdbcTemplate.query(sql, new ScheduleRepository.ScheduleRowMapper());
    }

    // 선택 일정 조회
    public ScheduleResponseDto findSelect(Long scheduleId) {
        // DB 조회
        String sql = "SELECT * FROM schedule WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{scheduleId}, new ScheduleRowMapper());
    }

    // 선택 일정 수정
    public void update(Long scheduleId, ScheduleRequestDto requestDto, String password) {

        String getPasswordSql = "SELECT password FROM schedule WHERE id = ?";
        String savedPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, scheduleId);

        if (!password.equals(savedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String sql = "UPDATE schedule SET title = ?, contents = ?, manager = ?, date = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getDate(), scheduleId);
    }

    // 선택 일정 삭제
    public void delete(Long scheduleId, String password) {

        String getPasswordSql = "SELECT password FROM schedule WHERE id = ?";
        String savedPassword = jdbcTemplate.queryForObject(getPasswordSql, String.class, scheduleId);

        if (!password.equals(savedPassword)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, scheduleId);
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
