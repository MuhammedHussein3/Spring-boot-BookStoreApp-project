package com.bookify.controller;

import com.bookify.dto.OrderItemDto;
import com.bookify.enitity.Order;
import com.bookify.enitity.OrderItem;
import com.bookify.exception.OrderNotFoundException;
import com.bookify.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    @PostMapping("/")
    Order addOrder(@RequestBody  Order order){
        return orderService.addOrder(order);
    }
    @GetMapping("/tot_price/{id}")
    public ResponseEntity<BigDecimal> totAmountPrice(@PathVariable Long id) throws OrderNotFoundException {
      return ResponseEntity.ok(orderService.totAmountPrice(id));
    }
    @GetMapping("/getOrderItems/")
   public ResponseEntity<List<OrderItemDto>> getOrderItemsForOrder(@RequestParam Long id) throws OrderNotFoundException {
        return ResponseEntity.ok(orderService.getOrderItemsForOrder(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long id) throws OrderNotFoundException {
      orderService.deleteOrderById(id);
      return ResponseEntity.ok("deleted successfully");
    }

}
