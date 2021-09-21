package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT o.productList from Order o where o.userId=:userId")
    List<Product> findProductByUserId(@Param("userId") long userId);
}
