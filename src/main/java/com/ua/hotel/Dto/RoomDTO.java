package com.ua.hotel.Dto;

import com.ua.hotel.domain.RoomType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class RoomDTO {

    private Long id;
    private GuestDTO guest;
    private String roomNumber;
    private RoomType roomType;
    private BigDecimal costPerNight;
}
