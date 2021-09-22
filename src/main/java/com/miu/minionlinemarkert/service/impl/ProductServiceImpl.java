package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.DTO.ProductComment;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.model.Review;
import com.miu.minionlinemarkert.repository.ProductRepository;
import com.miu.minionlinemarkert.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public List<Product> getAllProductWhichAreNotOutOfStock() {
        return productRepository.getAllProductWhichAreNotOutOfStock();
    }

    @Override
    public void updateProductQuantity(List<Product> productList) {
        List<Product> updatedProducts = new ArrayList<>();
        for (Product product : productList) {
            Product productFromDB = getById(product.getId());
            //productFromDB.setQuantity(productFromDB.getQuantity() - product.getQuantity());
            productFromDB.setModifiedDate(System.currentTimeMillis());
            updatedProducts.add(productFromDB);
        }
        productRepository.saveAll(updatedProducts);
    }

    @Override
    public void updateReview(Review review, Long productId) {
        Product product = getById(productId);
        if (product != null) {
            if (product.getReviews().size() > 0) {
                product.getReviews().add(review);
            } else {
                product.setReviews(Collections.singletonList(review));
            }
            save(product);
        }
    }

    @Override
    public List<Product> getPendingReviewProducts() {
        return productRepository.getPendingReviewApprovalProducts();

    }

    @Override
    public void saveAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    @Override
    public void updateProductReviewStatus(ProductComment productComment) {
        Product product = getById(productComment.getProductId());
        for (Review r : product.getReviews()) {
            if (r.getId() == productComment.getReviewId()) {
                r.setApproveStatus(productComment.isStatus());
                break;
            }
        }
        save(product);
    }
}
