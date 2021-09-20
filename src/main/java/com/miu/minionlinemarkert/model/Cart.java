package com.miu.minionlinemarkert.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class Cart {
    private List<Item> itemList;
}
