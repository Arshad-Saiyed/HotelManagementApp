package com.arshad.project.hotelManagementApp.dto;

import com.arshad.project.hotelManagementApp.entity.HotelContactInfo;
import lombok.Data;

@Data
public class HotelDto {

    private Long Id;

    private String name;

    private String city;

    private String[] photos;

    private String[] amenities;

    private HotelContactInfo contactInfo;

    private Boolean active;
}
