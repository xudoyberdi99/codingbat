package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LanguageDto {
    @NotNull(message = "name bush bulishi mumkin emas")
    private String name;
}
