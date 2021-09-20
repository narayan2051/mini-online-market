package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private int productId;
    private String productName;
    private String productDescription;
    private long price;
}
