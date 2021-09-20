package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Payment {
    private long paymentId;
    private LocalDate paidDate;
    private long totalAmount;
    private String details;
}
