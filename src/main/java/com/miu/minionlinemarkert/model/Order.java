package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    @Id
    private String id;
    private String userId;
    private List<Product> productList;
    private String orderStatus;
    private String billingAddress;
    private String shippingAddress;
    private long createdDate;
    private long modifiedDate;
}
