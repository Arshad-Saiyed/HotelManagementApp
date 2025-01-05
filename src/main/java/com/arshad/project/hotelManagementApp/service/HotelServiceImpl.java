package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.dto.HotelDto;
import com.arshad.project.hotelManagementApp.entity.Hotel;
import com.arshad.project.hotelManagementApp.entity.Room;
import com.arshad.project.hotelManagementApp.exception.ResourceNotFound;
import com.arshad.project.hotelManagementApp.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService{

    private final HotelRepository hotelRepository;

    private final ModelMapper modelMapper;

    private final InventoryService inventoryService;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {

        // TODO: need to perform request validation

        // convert DTO to Hotel entity
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);

        // initially set hotel active status as 'false'
        hotel.setActive(Boolean.FALSE);

        // save hotel
        hotelRepository.save(hotel);

        // log after creating
        log.info("Hotel created with ID: {}", hotel.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long hotelId) {

        // log before fetching hotel
        log.info("Fetching hotel with ID: {}", hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found"));

        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long hotelId, HotelDto hotelDto) {

        log.info("Updating hotel with ID: {}", hotelId);

        boolean isExist = hotelRepository.existsById(hotelId);
        if(!isExist) throw new ResourceNotFound("Hotel not found");

        Hotel hotelToUpdate = modelMapper.map(hotelDto, Hotel.class);
        hotelToUpdate.setId(hotelId);

        Hotel updatedHotel = hotelRepository.save(hotelToUpdate);

        log.info("Hotel updated with ID: {}", updatedHotel.getId());
        return modelMapper.map(updatedHotel, HotelDto.class);
    }

    @Override
    @Transactional
    public void deleteHotelById(Long hotelId) {
        log.info("Deleting hotel with ID: {}", hotelId);

        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFound("Hotel not found"));

        hotelRepository.deleteById(hotelId);

        for (Room room : hotel.getRooms()) {
            inventoryService.deleteFutureInventory(room);
        }
    }

    @Override
    @Transactional
    public void activateHotelById(Long hotelId) {
        log.info("Activating hotel with ID: {}", hotelId);
        // check if hotel exists
        Hotel hotel = hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFound("Hotel not found"));

        hotel.setActive(Boolean.TRUE);

        for (Room room : hotel.getRooms()) {
            inventoryService.createInitialInventory(room);
        }

        hotelRepository.save(hotel);

    }
}

