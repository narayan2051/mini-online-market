package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.OrderDTO;
import com.miu.minionlinemarkert.DTO.OrderStatus;
import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.Order;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.service.OrderService;
import com.miu.minionlinemarkert.service.ProductService;
import com.miu.minionlinemarkert.util.AppUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;
    private final AppUtil appUtil;
    private final ProductService productService;

    @Autowired
    public OrderController(OrderService orderService, ModelMapper modelMapper, AppUtil appUtil, ProductService productService) {
        this.orderService = orderService;
        this.modelMapper = modelMapper;
        this.appUtil = appUtil;
        this.productService = productService;
    }

    @PostMapping
    public ApiResponse save(@RequestBody @Valid OrderDTO orderDTO, Authentication authentication) {
        Order order = modelMapper.map(orderDTO, Order.class);
        productService.updateProductQuantity(order.getProductList());
        order.setUserId(appUtil.getUserByAuthentication(authentication).getId());
        order.setOrderStatus(AppConstant.ORDER_PLACED);
        orderService.save(order);
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.ORDER_SAVED);
    }

    @GetMapping("/loggedInUserProducts")
    public List<Product> getProductsByUserId(Authentication authentication){
        return  orderService.findProductByUserId(appUtil.getUserByAuthentication(authentication).getId());
    }

    @GetMapping
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @PostMapping("/orderstatus")
    public ApiResponse updateOrderStatus(@RequestBody OrderStatus orderStatus){
        orderService.updateOrderStatus(orderStatus);
        return new ApiResponse(ResponseConstant.SUCCESS,ResponseConstant.ORDER_STATUS_UPDATED);
    }

}
