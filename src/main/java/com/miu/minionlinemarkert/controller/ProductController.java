package com.miu.minionlinemarkert.controller;

import com.miu.minionlinemarkert.DTO.ApiResponse;
import com.miu.minionlinemarkert.DTO.ProductComment;
import com.miu.minionlinemarkert.DTO.ProductDTO;
import com.miu.minionlinemarkert.DTO.ReviewDTO;
import com.miu.minionlinemarkert.constant.AppConstant;
import com.miu.minionlinemarkert.constant.ResponseConstant;
import com.miu.minionlinemarkert.model.Product;
import com.miu.minionlinemarkert.model.Review;
import com.miu.minionlinemarkert.service.ProductService;
import com.miu.minionlinemarkert.util.AppUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
    private final AppUtil appUtil;

    @Autowired
    public ProductController(ProductService productService, ModelMapper modelMapper, AppUtil appUtil) {
        this.productService = productService;
        this.modelMapper = modelMapper;
        this.appUtil = appUtil;
    }

    @GetMapping
//    @PreAuthorize("hasAuthority(" + AppConstant.SELLER + ")")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/stock")
    public List<Product> findAllProductWhoseQuantityIsGreaterThanZero() {
        return productService.getAllProductWhichAreNotOutOfStock();
    }

    @PostMapping
    public ApiResponse save(@RequestBody @Valid ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        productService.save(product);
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.PRODUCT_ADDED);
    }


    @PostMapping("/review")
    public ApiResponse updateReview(@RequestBody ReviewDTO reviewDTO, Authentication authentication) {
        Review review = new Review();
        review.setReviewText(review.getReviewText());
        review.setUserId(appUtil.getUserByAuthentication(authentication).getId());
        review.setApproveStatus(false);
        productService.updateReview(review, reviewDTO.getProductId());
        return new ApiResponse(ResponseConstant.SUCCESS, ResponseConstant.REVIEW_ADDED);
    }

    @PreAuthorize("hasAuthority(" + AppConstant.ADMIN + ")")
    @GetMapping("/pending-review")
    public List<Product> getPendingReviewProducts() {
        return productService.getPendingReviewProducts();
    }

    @PreAuthorize("hasAuthority(" + AppConstant.ADMIN + ")")
    @PostMapping("/update-review-status")
    public ApiResponse updateProductReviewStatus(@RequestBody ProductComment productComment){
        productService.updateProductReviewStatus(productComment);
        return new ApiResponse(ResponseConstant.SUCCESS,ResponseConstant.COMMENT_STATUS_UPDATED);
    }

}
