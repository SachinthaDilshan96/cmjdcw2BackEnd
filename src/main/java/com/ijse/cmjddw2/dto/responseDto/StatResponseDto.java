package com.ijse.cmjddw2.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class StatResponseDto {
    private double lastDayTotalSales;
    private int lastDayOrderCount;
    private long totalProducts;
    private int totalLowStocks;
    private int todayExpires;
}
