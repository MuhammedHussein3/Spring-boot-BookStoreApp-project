package com.bookify.dto;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class ReviewDto {
    private String username;
    private String name;
    private int rating;
    private String comment;
}
