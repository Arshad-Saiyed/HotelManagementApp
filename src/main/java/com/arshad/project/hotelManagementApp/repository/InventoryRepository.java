package com.arshad.project.hotelManagementApp.repository;

import com.arshad.project.hotelManagementApp.entity.Inventory;
import com.arshad.project.hotelManagementApp.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    void deleteByDateAfterAndRoom(LocalDate date, Room room);
}
