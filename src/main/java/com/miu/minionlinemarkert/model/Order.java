package com.miu.minionlinemarkert.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate orderDate;

    @NotNull
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    @Future
    private LocalDate shippingDate;

    @Valid
    @OneToOne
    private Address shippingAddress;

    @Valid
    @OneToOne
    private Address billingAddress;

    @ManyToOne
    private AppUser user;

    @OneToMany(mappedBy = "order")
    private List<Product> productList;

    @OneToOne
    private Payment payment;
}
