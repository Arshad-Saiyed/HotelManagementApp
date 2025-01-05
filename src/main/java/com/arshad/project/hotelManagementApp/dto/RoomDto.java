package com.arshad.project.hotelManagementApp.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class RoomDto {

    private Long id;

    private String roomType;

    private BigDecimal basePrice;

    private Integer totalCount;

    private Integer capacity;

    private String[] photos;

    private String[] amenities;
}
