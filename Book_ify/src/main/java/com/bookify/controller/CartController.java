package com.bookify.controller;

import com.bookify.enitity.OrderItem;
import com.bookify.exception.OrderItemNoFoundException;
import com.bookify.exception.OrderItemQuantityException;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.service.impl.CartServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orderItems")
@Validated
public class CartController {
    @Autowired
    private CartServiceImpl cartService;
    @PostMapping("/")
    OrderItem addOrderItem(@RequestBody  OrderItem orderItem) throws ProductNotFoundException, OrderItemQuantityException {
        return cartService.addOrderItem(orderItem);
   }
   @GetMapping("/{id}")
    public OrderItem findOrderItemById(@PathVariable Long id) throws OrderItemNoFoundException {
      return cartService.findOrderItemById(id);
    }
    @PutMapping("/")
    public String updateOrderItem(@RequestParam int quantity,@RequestParam Long id) throws OrderItemNoFoundException {
        return cartService.updateOrderItem(quantity,id);
    }
    @DeleteMapping("/")
    public ResponseEntity<String> deleteOrderItem(@RequestParam Long id) throws OrderItemNoFoundException {
        return ResponseEntity.ok(cartService.deleteOrderItem(id));
    }
}
