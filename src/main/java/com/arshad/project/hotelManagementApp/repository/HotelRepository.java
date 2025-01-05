package com.arshad.project.hotelManagementApp.repository;

import com.arshad.project.hotelManagementApp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}
