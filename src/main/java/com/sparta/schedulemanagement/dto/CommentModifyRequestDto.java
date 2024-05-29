package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter

public class CommentModifyRequestDto {
    private Long id;

    @NotBlank(message = "내용을 입력해 주세요")
    private String commentContents;
}
