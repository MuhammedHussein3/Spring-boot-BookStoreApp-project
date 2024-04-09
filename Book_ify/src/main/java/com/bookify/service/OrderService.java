package com.bookify.service;

import com.bookify.dto.OrderItemDto;
import com.bookify.enitity.Order;
import com.bookify.enitity.OrderItem;
import com.bookify.exception.OrderNotFoundException;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

      Order findOrderById(Long id) throws OrderNotFoundException;
      BigDecimal totAmountPrice(Long id) throws OrderNotFoundException;
      List<OrderItemDto> getOrderItemsForOrder(Long id) throws OrderNotFoundException;
      List<OrderItem> ORDER_ITEMS(Long id) throws OrderNotFoundException;
      Order addOrder(Order order);
      void updateTotPriceForOrder(Long id, BigDecimal tot);
      void deleteOrderById(Long id) throws OrderNotFoundException;

}
