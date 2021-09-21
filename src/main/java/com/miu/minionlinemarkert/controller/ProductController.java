package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.ProductDTO;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper) {
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<Product> findAll() {
        return productService.findAll();
    }

    @PostMapping
    public ApiResponse save(@RequestBody @Valid ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productService.save(product);
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.PRODUCT_ADDED);
    }
}
