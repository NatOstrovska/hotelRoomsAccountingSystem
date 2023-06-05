package com.ua.hotel.rest;

import com.ua.hotel.Dto.GuestDTO;
import com.ua.hotel.service.GuestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class GuestController {

    @Autowired
    private final GuestService guestService;

    @PostMapping("/guest")
    public GuestDTO create(GuestDTO guest) {

        return guestService.create(guest);
    }

    @PutMapping("/guest")
    public ResponseEntity<GuestDTO> updateGuest(@RequestBody GuestDTO guest) {

        Optional<GuestDTO> guestOptional = guestService.updateGuest(guest);
        return guestOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/guest/byLastName/{lastName}")
    public ResponseEntity<GuestDTO> getByLastName(@PathVariable String lastName) {

        Optional<GuestDTO> guest = guestService.getByLastName(lastName);

        return guest.isPresent()?  ResponseEntity.ok(guest.get()): ResponseEntity.notFound().build();
    }


    @GetMapping("/guest/byPassportNumber/{passportNumber}")
    public ResponseEntity<GuestDTO> getByPassportNumber(@PathVariable String passportNumber) {

        Optional<GuestDTO> guest = guestService.getByPassportNumber(passportNumber);

        return guest.isPresent()?  ResponseEntity.ok(guest.get()): ResponseEntity.notFound().build();
    }
}
