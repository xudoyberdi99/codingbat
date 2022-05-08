package com.example.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoryDto {
    @NotNull(message = "nomi bush bulishi mumkin emas")
    private String name;
    @NotNull(message = "deskription bush bulmasin")
    private String description;
    @NotNull
    private Integer languageId;
}
