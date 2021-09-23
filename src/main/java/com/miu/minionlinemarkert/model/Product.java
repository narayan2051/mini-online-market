package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private double quantity;
    private String description;
    private BigDecimal price;
    private String category;
    private List<Review> reviews;
    private long createdDate;
    private long modifiedDate;
}
