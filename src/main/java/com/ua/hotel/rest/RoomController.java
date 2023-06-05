package com.ua.hotel.rest;

import com.ua.hotel.Dto.GuestDTO;
import com.ua.hotel.Dto.RoomDTO;
import com.ua.hotel.service.GuestService;
import com.ua.hotel.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class RoomController {
    @Autowired
    private GuestService guestService;
    @Autowired
    private RoomService roomService;
    @Autowired
    private ModelMapper modelMapper;

    @PutMapping("/room/{roomNumber}")
    public ResponseEntity<?> checkIN(@RequestBody GuestDTO guestDTO, @PathVariable String roomNumber) {

        Optional<GuestDTO> guestOptional;
        guestOptional = guestService.getByPassportNumber(guestDTO.getPassportNumber());

        if (!guestOptional.isPresent()) {
            guestDTO = guestService.create(guestDTO);
        }

        Optional<RoomDTO> rooms = roomService.findByRoomNumber(roomNumber);

        if (rooms.isPresent() && rooms.get().getGuest() == null) {
            RoomDTO room = rooms.get();
            room.setGuest(guestDTO);
            roomService.updateRoom(room);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Room is occupied");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Checked in");
    }

    @DeleteMapping("/room/{roomNumber}")
    public ResponseEntity<?> checkOUT(@PathVariable String roomNumber) {

        Optional<RoomDTO> rooms = roomService.findByRoomNumber(roomNumber);

        if (rooms.isPresent() && rooms.get().getGuest() != null) {
            RoomDTO room = rooms.get();
            room.setGuest(null);
            roomService.updateRoom(room);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Wrong room number");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Checked out");
    }

    @PostMapping("/room")
    public RoomDTO create(RoomDTO roomDTO) {

        return roomService.create(roomDTO);
    }
}



