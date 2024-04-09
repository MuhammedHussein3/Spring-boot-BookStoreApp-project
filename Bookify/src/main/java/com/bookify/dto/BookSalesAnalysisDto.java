package com.bookify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookSalesAnalysisDto {

    private String bookName;
    private Long bookId;
//    private Year year;
    private int quantity;
    private double price;
}
