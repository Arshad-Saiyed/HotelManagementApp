package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.dto.HotelDto;

public interface HotelService {
    HotelDto createHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long hotelId);

    HotelDto updateHotelById(Long hotelId, HotelDto hotelDto);

    void deleteHotelById(Long hotelId);

    void activateHotelById(Long hotelId);
}
