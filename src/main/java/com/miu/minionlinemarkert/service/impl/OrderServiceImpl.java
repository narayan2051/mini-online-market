package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.DTO.OrderStatus;
import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.repository.OrderRepository;
import com.miu.minionlinemarkert.service.OrderService;
import com.miu.minionlinemarkert.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.mongoTemplate = mongoTemplate;
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
    public Order getById(String id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public List<Product> findProductByUserId(String userId) {
        return orderRepository.findProductByUserId(userId);
    }

    @Override
    public void updateOrderStatus(OrderStatus orderStatus) {
        Order order = getById(orderStatus.getOrderId());
        if (orderStatus.getStatus().equals(AppConstant.ORDER_CANCELLED)) {
            List<Product> products = new ArrayList<>();
            for (Product product : order.getProductList()) {
                Product productFromDB = productService.getById(product.getId());
                productFromDB.setQuantity(productFromDB.getQuantity() + product.getQuantity());
                products.add(productFromDB);
            }
            productService.saveAll(products);
        }
        order.setOrderStatus(orderStatus.getStatus());
        save(order);
    }

    @Override
    public List<Order> orderBasedOnLoggedInUser(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(id));
        return mongoTemplate.find(query, Order.class);
    }
}
