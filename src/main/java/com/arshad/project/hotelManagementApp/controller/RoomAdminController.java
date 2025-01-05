package com.arshad.project.hotelManagementApp.controller;

import com.arshad.project.hotelManagementApp.dto.RoomDto;
import com.arshad.project.hotelManagementApp.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/hotels/{hotelId}/rooms")
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        RoomDto room = roomService.createRoom(hotelId, roomDto);
        return new ResponseEntity<>(room, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms(@PathVariable Long hotelId) {
        return ResponseEntity.ok(roomService.getHotelRooms(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable Long hotelId, @PathVariable Long roomId) {
        RoomDto room = roomService.getRoomById(roomId);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long hotelId, @PathVariable Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();
    }

}
