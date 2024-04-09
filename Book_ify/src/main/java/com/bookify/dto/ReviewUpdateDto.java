package com.bookify.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jdk.jshell.execution.LoaderDelegate;
import lombok.Data;

@Data
public class ReviewUpdateDto {
    @NotNull
    private Long id;
    @Min(0)
    @Max(5)
    private int rating;
    @NotBlank
    private String comment;
}
