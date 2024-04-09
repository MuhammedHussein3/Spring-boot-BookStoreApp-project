package com.bookify.service;

import com.bookify.Projctions.BookSalesAnalysis;
import com.bookify.enitity.OrderItem;
import com.bookify.enitity.Book;
import com.bookify.exception.OrderItemNoFoundException;
import com.bookify.exception.OrderItemQuantityException;

import java.util.List;

public interface CartService {

    OrderItem addOrderItem(OrderItem orderItem) throws OrderItemQuantityException;
    OrderItem findOrderItemById(Long id) throws OrderItemNoFoundException;
    boolean getAllQuantityForOrderItem(OrderItem orderItem,int q);
    double calcTotalPrice(int quantity,double price);

    String updateOrderItem(int quantity,Long id) throws OrderItemNoFoundException;
    String deleteOrderItem(Long id) throws OrderItemNoFoundException;

}
