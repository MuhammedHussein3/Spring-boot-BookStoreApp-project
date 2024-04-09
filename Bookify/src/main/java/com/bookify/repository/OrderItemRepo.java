package com.bookify.repository;

import com.bookify.Projctions.BookSalesAnalysis;
import com.bookify.enitity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(readOnly = false)
public interface
OrderItemRepo extends JpaRepository<OrderItem,Long> {

   @Modifying
    @Query("""
UPDATE OrderItem
  set quantity = :quantity, tot_price =:price
     WHERE id = :id
""")
    void updateQuantityForOrderItem(int quantity,double price,Long id);
  @Modifying
  @Query("""
DELETE OrderItem
WHERE id = :id
""")
    void deleteOrderItem(Long id);


}
