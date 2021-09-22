package com.miu.minionlinemarkert.service;

import com.miu.minionlinemarkert.model.Product;

import java.util.List;


public interface ProductService extends GenericService<Product> {
     List<Product> getAllProductWhichAreNotOutOfStock();

     void updateProductQuantity(List<Product> productList);
}