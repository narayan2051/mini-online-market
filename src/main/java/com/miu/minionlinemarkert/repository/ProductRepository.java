package com.miu.minionlinemarkert.repository;

import com.miu.minionlinemarkert.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
   // @Query("select p from Product p where p.quantity > 0")
//    List<Product> getAllProductWhichAreNotOutOfStock();
//
//   // @Query("select p from Product p where p.reviews.size>0")
//    List<Product> getPendingReviewApprovalProducts();
}
