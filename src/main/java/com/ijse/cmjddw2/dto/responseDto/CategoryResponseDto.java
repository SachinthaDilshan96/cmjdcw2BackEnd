package com.ijse.cmjddw2.dto.responseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CategoryResponseDto {
    private int id;
    private String categoryName;
    private String addedBy;
    private long addedOn;
}
