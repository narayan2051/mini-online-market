package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
   // @Query("SELECT o.productList from Order o where o.userId=:userId")

    List<Product> findProductByUserId(@Param("userId") String userId);
}
