package com.ijse.cmjddw2.dto.requestDto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    private List<Integer> products;
}
