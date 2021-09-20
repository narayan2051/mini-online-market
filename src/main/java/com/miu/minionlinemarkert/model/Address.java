package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class Address {
    @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotNull
    @Size(min=5, max=5, message="zipcode should have size of five number.")
    private String zipCode;

    @NotBlank
    private String state;

    @NotNull
    @Size(min=10, max=10, message="phone number have size of 10.")
    private long phoneNumber;
}
