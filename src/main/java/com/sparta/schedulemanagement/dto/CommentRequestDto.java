package com.sparta.schedulemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long id;

    @NotBlank(message = "내용을 입력해 주세요")
    private String commentContents;

    @NotBlank(message = "사용자 이름을 입력해 주세요")
    private String userId;

}
