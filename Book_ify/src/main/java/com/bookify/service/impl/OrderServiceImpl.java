package com.bookify.service.impl;

import com.bookify.dto.OrderItemDto;
import com.bookify.enitity.Order;
import com.bookify.enitity.OrderItem;
import com.bookify.exception.OrderNotFoundException;
import com.bookify.repository.OrderRepo;
import com.bookify.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class OrderServiceImpl implements OrderService {
   @Autowired
    private OrderRepo orderRepo;

    public Order findOrderById(Long id) throws OrderNotFoundException {
     Optional<Order> order =orderRepo.findById(id);
     if (order.isPresent())
         return order.get();

       throw new OrderNotFoundException(String.format("Not found order with %id", id));
    }

    @Transactional
    @Override
     public BigDecimal totAmountPrice(Long id) throws OrderNotFoundException {
        Order order = findOrderById(id);
        order.calculateTotalAmount();
        updateTotPriceForOrder(id,order.getTotAmountPrice());
        return order.getTotAmountPrice();
    }


    @Override
    public List<OrderItemDto> getOrderItemsForOrder(Long id) throws OrderNotFoundException {

        List<OrderItemDto> itemDtos = new ArrayList<>();
        ORDER_ITEMS(id).forEach(orderItem -> {
            OrderItemDto orderItemDto = OrderItemDto.build(orderItem.getBook().getName(),orderItem.getTot_price(),orderItem.getQuantity());
            itemDtos.add(orderItemDto);
        });
        return itemDtos;
    }
    @Override
    public List<OrderItem> ORDER_ITEMS(Long id) throws OrderNotFoundException {
        Order order = findOrderById(id);
        return order.getOrderItems();
    }

    @Override
    public Order addOrder(Order order) {
        order.setLocalDateTime(LocalDateTime.now());
        return orderRepo.save(order);
    }
    @Override
    public void updateTotPriceForOrder(Long id, BigDecimal tot) {
        orderRepo.updateTotPriceForOrder(id,tot);
    }

    @Transactional
    @Override
    public void deleteOrderById(Long id) throws OrderNotFoundException {
        findOrderById(id);
        orderRepo.deleteOrderById(id);
    }


}
