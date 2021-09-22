package com.miu.minionlinemarkert.service;

import com.miu.minionlinemarkert.DTO.ProductComment;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.model.Review;

import java.util.List;


public interface ProductService extends GenericService<Product> {
    List<Product> getAllProductWhichAreNotOutOfStock();

    void updateProductQuantity(List<Product> productList);

    void updateReview(Review review, Long productId);

    List<Product> getPendingReviewProducts();

    void saveAll(List<Product> productList);

    void updateProductReviewStatus(ProductComment productComment);
}