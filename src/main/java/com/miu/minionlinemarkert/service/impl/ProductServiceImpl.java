package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.repository.ProductRepository;
import com.miu.minionlinemarkert.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void save(Product product) {
        if (product.getId() != null) {
            product.setCreatedDate(getById(product.getId()).getCreatedDate());
            product.setModifiedDate(System.currentTimeMillis());
        } else {
            product.setCreatedDate(System.currentTimeMillis());
        }
        productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
