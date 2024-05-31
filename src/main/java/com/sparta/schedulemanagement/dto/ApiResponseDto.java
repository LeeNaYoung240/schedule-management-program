package com.sparta.schedulemanagement.dto;

public class ApiResponseDto {
    private String msg;
    private int statusCode;

    public ApiResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    // Getter와 Setter 메소드
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}