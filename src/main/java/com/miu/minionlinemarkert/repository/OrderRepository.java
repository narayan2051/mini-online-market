package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Product> findByUserId(String userId);
}
