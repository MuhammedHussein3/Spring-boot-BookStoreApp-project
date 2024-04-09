package com.bookify.repository;

import com.bookify.enitity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    @Modifying
    @Query("""
update Order
set totAmountPrice = :tot
where id = :id
""")
    void updateTotPriceForOrder(Long id, BigDecimal tot);

    @Modifying
    void deleteOrderById(Long id);


}
