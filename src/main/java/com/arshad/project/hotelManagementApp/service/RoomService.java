package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.dto.RoomDto;
import java.util.List;

public interface RoomService {

    RoomDto createRoom(Long hotelId, RoomDto roomDto);

    List<RoomDto> getHotelRooms(Long hotelId);

    RoomDto getRoomById(Long roomId);

    void deleteRoomById(Long roomId);
}
