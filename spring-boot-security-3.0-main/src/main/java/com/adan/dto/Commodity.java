package com.adan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commodity {

    private int commodityId;
    private String name;
    private int qty;
    private double price;
}
