package com.bookify.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookResponseDto {

    private String title;
    private String edition;
    private double price;
    private double priceAfterDiscount;
    private String description;
    private String authorName;
    private LocalDateTime publishBook;
    private String categoryName;
    /*
     String getTitle();
     String getEdition();
     double getPrice();
     double getPriceAfterDiscount();
     String description();
     String getAuthorName();
     String getPublishBook();
     String categoryName();
     */
}
