package com.ijse.cmjddw2.dto.requestDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductRequestDto {
    private String name;
    private int categoryId;
    private int qty;
    private double unitPrice;
}
