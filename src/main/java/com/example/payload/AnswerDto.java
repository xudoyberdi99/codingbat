package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AnswerDto {
    @NotNull(message = "text bush bulmasin")
    private String text;
    @NotNull(message = "task id ni kiriting")
    private Integer taskId;
    @NotNull(message = "emailni kiriting")
    private String email;

    private boolean isCorrect;
}
