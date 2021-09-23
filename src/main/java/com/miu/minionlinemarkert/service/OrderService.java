package com.miu.minionlinemarkert.service;

import com.miu.minionlinemarkert.DTO.OrderStatus;
import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;

import java.util.List;

public interface OrderService extends GenericService<Order>{
    List<Product> findProductByUserId(String userId);

    void updateOrderStatus(OrderStatus orderStatus);
}
