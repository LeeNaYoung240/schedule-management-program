package com.sparta.schedulemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class Exception {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        String error = e.getMessage();
        if(error.contains("비밀번호가 일치하지 않습니다."))
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
        else if(error.contains("선택한 일정은 존재하지 않습니다."))
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        else if(error.contains("이미 삭제된 일정입니다."))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}