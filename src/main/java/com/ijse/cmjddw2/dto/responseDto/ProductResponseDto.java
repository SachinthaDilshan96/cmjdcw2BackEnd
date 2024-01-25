package com.ijse.cmjddw2.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponseDto {
    private int id;
    private String name;
    private int qty;
    private int category;
    private double unitPrice;
}
