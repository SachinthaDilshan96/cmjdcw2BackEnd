package com.ijse.cmjddw2.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class DailySalesResponseDto {
    private double sales;
    private long date;
}
