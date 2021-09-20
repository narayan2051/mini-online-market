package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class Order {
    private long orderId;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate orderDate;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Future
    private LocalDate shippingDate;

    @Valid
    private Address shippingAddress;

    @Valid
    private Address billingAddress;

    private List<Item> itemList;
}
