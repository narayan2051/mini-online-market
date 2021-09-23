package com.miu.minionlinemarkert.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private String id;
    @NotEmpty
    @Size(min = 2)
    private String title;
    @Min(1)
    private double quantity;
    @NotEmpty
    @Size(min = 1)
    private String description;
    @NotNull
    @Min(0)
    private BigDecimal price;
    @NotEmpty
    @Size(min = 1)
    private String category;
}
