package com.arshad.project.hotelManagementApp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "inventory",
        uniqueConstraints = @UniqueConstraint(name = "unique_hotel_room_date", columnNames = { "hotel_id", "room_id", "date"})
)
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Room room;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer bookedCount;

    @Column(nullable = false)
    private Integer totalCount;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal surgeFactor;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // price = base price * surge factor

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Boolean closed;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CreationTimestamp
    private LocalDateTime updatedAt;

}
