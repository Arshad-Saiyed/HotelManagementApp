package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.dto.RoomDto;
import com.arshad.project.hotelManagementApp.entity.Hotel;
import com.arshad.project.hotelManagementApp.entity.Room;
import com.arshad.project.hotelManagementApp.exception.ResourceNotFound;
import com.arshad.project.hotelManagementApp.repository.HotelRepository;
import com.arshad.project.hotelManagementApp.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomServiceImp implements RoomService {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;
    private final InventoryService inventoryService;
    private final ModelMapper modelMapper;

    @Override
    public RoomDto createRoom(Long hotelId, RoomDto roomDto) {
        log.info("Creating a new room for hotelId: " + hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found"));
        
        Room room = modelMapper.map(roomDto, Room.class);
        room.setHotel(hotel);
        Room createdRoom = roomRepository.save(room);
        log.info("Created a new room with roomId: {} for hotelId: {}", hotelId, createdRoom.getId());

        if (hotel.getActive()) {
            inventoryService.createInitialInventory(createdRoom);
        }

        return modelMapper.map(createdRoom, RoomDto.class);
    }

    @Override
    public List<RoomDto> getHotelRooms(Long hotelId) {
        log.info("Getting all the rooms for hotelId: {}", hotelId);

        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel not found"));


        return hotel.getRooms()
                .stream()
                .map((room) -> modelMapper.map(room, RoomDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto getRoomById(Long roomId) {
        log.info("Getting room by roomId: {}", roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room not found"));
        return modelMapper.map(room, RoomDto.class);
    }

    @Transactional
    @Override
    public void deleteRoomById(Long roomId) {
        log.info("Deleting room by roomId: {}", roomId);

        Room room = roomRepository.findById(roomId).orElseThrow(() -> new ResourceNotFound("Room not found"));
        inventoryService.deleteFutureInventory(room);
        roomRepository.deleteById(roomId);

    }
}
