package com.ekko.easy.buy.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Cart {
    private Integer               userId;
    private List<Integer>         items        = new ArrayList<>();
    private Map<Integer, Integer> itemQuantity = new HashMap<>();
}
