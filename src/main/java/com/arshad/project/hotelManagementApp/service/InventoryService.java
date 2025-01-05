package com.arshad.project.hotelManagementApp.service;

import com.arshad.project.hotelManagementApp.entity.Room;

public interface InventoryService {
     void createInitialInventory(Room room);

     void deleteFutureInventory(Room room);
}
