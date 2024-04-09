package com.bookify.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
public class CategoryDto implements Serializable {

    String bookName;
    String edition;
    String authorName;
    LocalDateTime publishBook;
}
