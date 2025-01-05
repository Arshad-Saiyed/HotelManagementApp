package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.entity.Inventory;
import com.arshad.project.hotelManagementApp.entity.Room;
import com.arshad.project.hotelManagementApp.repository.InventoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Slf4j
@Data
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    // In years
    private static final int INITIAL_INVENTORY_SIZE = 1;
    private static final int BOOKED_COUNT = 0;

    @Override
    public void createInitialInventory(Room room) {

        log.info("Creating initial inventory for {} year", INITIAL_INVENTORY_SIZE);

        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusYears(INITIAL_INVENTORY_SIZE);

        populateInventory(room, currentDate, endDate);

    }

    @Override
    public void deleteFutureInventory(Room room) {
        LocalDate currentDate = LocalDate.now();
        inventoryRepository.deleteByDateAfterAndRoom(currentDate, room);
    }

    public void populateInventory(Room room, LocalDate currentDate, LocalDate endDate) {
        for (; !currentDate.isAfter(endDate); currentDate = currentDate.plusDays(1)) {
            Inventory inventory = Inventory
                    .builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(BOOKED_COUNT)
                    .city(room.getHotel().getCity())
                    .surgeFactor(BigDecimal.ONE)
                    .price(room.getBasePrice())
                    .date(currentDate)
                    .closed(Boolean.FALSE)
                    .totalCount(room.getTotalCount())
                    .build();
            inventoryRepository.save(inventory);
        }
    }
}
