package com.miu.minionlinemarkert.DTO;

import com.miu.minionlinemarkert.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long id;
    @Size(min = 1)
    private List<Product> productList;
    private String billingAddress;
    private String shippingAddress;
}
