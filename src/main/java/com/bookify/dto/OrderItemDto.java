package com.bookify.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
@Data
public class OrderItemDto {



    private String bookName;
    double tot_price;
    private int quantity;
}
