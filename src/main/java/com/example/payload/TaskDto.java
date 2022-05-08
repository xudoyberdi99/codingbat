package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class TaskDto {
    @NotNull(message = "name bush bulmasin")
    private String name;
    @NotNull(message = "text bush bulmasin")
    private String text;

    private String solution;

    private String hint;
    @NotNull(message = "nethod bush bulmasin")
    private String method;

    private boolean hasStar;
    @NotNull(message = "qaysi tilga tegishliligini tanlang")
    private Integer laguageId;

}
