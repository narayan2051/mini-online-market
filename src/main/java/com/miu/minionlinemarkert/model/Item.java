package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Item {
    private int quantity;
    private long price;
    private Product product;
}
