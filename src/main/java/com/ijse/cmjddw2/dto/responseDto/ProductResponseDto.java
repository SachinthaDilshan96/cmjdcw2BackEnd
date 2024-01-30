package com.ijse.cmjddw2.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductResponseDto {
    private int id;
    private String name;
    private int qty;
    private int initialQty;
    private int category;
    private double unitPrice;
    private long expireDate;
}
