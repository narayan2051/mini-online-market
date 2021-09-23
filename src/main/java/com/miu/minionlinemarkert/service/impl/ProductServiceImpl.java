package com.miu.minionlinemarkert.service.impl;

import com.miu.minionlinemarkert.DTO.ProductComment;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.model.Review;
import com.miu.minionlinemarkert.repository.ProductRepository;
import com.miu.minionlinemarkert.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, MongoTemplate mongoTemplate) {
        this.productRepository = productRepository;
        this.mongoTemplate = mongoTemplate;
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
    public Product getById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductWhichAreNotOutOfStock() {

        Query query= new Query();
        query.addCriteria(Criteria.where("quantity").gt(0));
        return mongoTemplate.find(query,Product.class);
    }

    @Override
    public void updateProductQuantity(List<Product> productList) {
        List<Product> updatedProducts = new ArrayList<>();
        for (Product product : productList) {
            Product productFromDB = getById(product.getId());
            productFromDB.setQuantity(productFromDB.getQuantity() - product.getQuantity());
            productFromDB.setModifiedDate(System.currentTimeMillis());
            updatedProducts.add(productFromDB);
        }
        productRepository.saveAll(updatedProducts);
    }

    @Override
    public void updateReview(Review review, String productId) {
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

        Q

//        return productRepository.getPendingReviewApprovalProducts();
        return null;

    }

    @Override
    public void saveAll(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    @Override
    public void updateProductReviewStatus(ProductComment productComment) {
        Product product = getById(productComment.getProductId());
        for (Review r : product.getReviews()) {
            if (r.getId().equals(productComment.getReviewId())) {
                r.setApproveStatus(productComment.isStatus());
                break;
            }
        }
        save(product);
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }
}
