package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.repository.OrderRepository;
import com.miu.minionlinemarkert.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {
        if (null != order.getId()) {
            order.setCreatedDate(getById(order.getId()).getCreatedDate());
            order.setModifiedDate(System.currentTimeMillis());
        } else {
            order.setCreatedDate(System.currentTimeMillis());
        }
        orderRepository.save(order);
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.getById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Product> findProductByUserId(long userId) {
        return orderRepository.findProductByUserId(userId);
    }
}
