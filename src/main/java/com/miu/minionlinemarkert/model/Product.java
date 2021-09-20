package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private int quantity;
    private String productName;
    private long price;
    private String productDescription;

    @ManyToOne
    private Order order;

    @ManyToOne
    private Cart cart;
}
