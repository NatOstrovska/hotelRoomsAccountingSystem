package com.ua.hotel.Dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class GuestDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String passportNumber;
    private String contact;
}
