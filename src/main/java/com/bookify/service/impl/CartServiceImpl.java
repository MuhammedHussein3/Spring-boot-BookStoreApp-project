package com.bookify.service.impl;

import com.bookify.Projctions.BookSalesAnalysis;
import com.bookify.enitity.Book;
import com.bookify.enitity.Order;
import com.bookify.enitity.OrderItem;
import com.bookify.exception.OrderItemNoFoundException;
import com.bookify.exception.OrderItemQuantityException;
import com.bookify.exception.ProductNotFoundException;
import com.bookify.repository.OrderItemRepo;

import com.bookify.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderItemRepo orderItemRepo;
    @Autowired BookServiceImpl bookService;
    Logger log = LoggerFactory.getLogger(CartServiceImpl.class);
    @Override
    public OrderItem addOrderItem(OrderItem orderItem) throws OrderItemQuantityException {
        if (getAllQuantityForOrderItem(orderItem,orderItem.getQuantity())) {
           try {
               Order order = orderItem.getOrder();
               order.addOrderItem(orderItem);
               Book book = bookService.findBookByName(orderItem.getBook().getName());
               orderItem.setTot_price(calcTotalPrice(orderItem.getQuantity(),book.getPriceAfterProduct()));
           }catch (NullPointerException e){
             log.info("Error message >> "+e.getMessage());
             throw new NullPointerException();
           } catch (ProductNotFoundException e) {
               log.info("Error message >> "+e.getMessage());
               throw new RuntimeException(e);
           }
            return orderItemRepo.save(orderItem);
        }
        throw new OrderItemQuantityException("not found books enough");
    }
    @Override
    public boolean getAllQuantityForOrderItem(OrderItem orderItem,int q){
        Book book = bookService.SearchForAdd(orderItem.getBook().getName(),orderItem.getBook().getEdition());
        return q<=book.getQuantityInStock();
    }

    @Override
    public OrderItem findOrderItemById(Long id) throws OrderItemNoFoundException {
        Optional<OrderItem> orderItem = orderItemRepo.findById(id);
        if (orderItem.isPresent())
            return orderItem.get();
        throw new OrderItemNoFoundException(String.format("Not found orderItem with %s id",id));
    }

    @Override
    public double calcTotalPrice(int quantity,double price) {
        return quantity * price;
    }

    @Override
    public String updateOrderItem(int quantity,Long id) throws OrderItemNoFoundException {
        OrderItem orderItem = findOrderItemById(id);

        if (!getAllQuantityForOrderItem(orderItem,quantity))
        {
           return  "Not found enough quantity for order this "+orderItem.getBook().getName();
        }
        double price = orderItem.getBook().getPriceAfterProduct();
        orderItemRepo.updateQuantityForOrderItem(quantity,calcTotalPrice(quantity,price),id);
        return "Update successfully";
    }
    @Override
    public String deleteOrderItem(Long id) throws OrderItemNoFoundException {
       OrderItem orderItem = findOrderItemById(id);
       if (orderItem!=null) {
           orderItemRepo.deleteOrderItem(id);
           return "deleted successfully";
       }
       return "deleted Unsuccessfully";
    }

}
