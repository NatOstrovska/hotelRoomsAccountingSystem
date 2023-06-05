package com.ua.hotel.service;

import com.ua.hotel.Dto.RoomDTO;
import com.ua.hotel.domain.Room;
import com.ua.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Optional<RoomDTO> findByRoomNumber(String roomNumber) {

        Optional<Room> room = roomRepository.findByRoomNumber(roomNumber);
        return Optional.ofNullable(modelMapper.map(room, RoomDTO.class));
    }

    public RoomDTO create(RoomDTO roomDTO) {

        Room room = roomRepository.save(modelMapper.map(roomDTO, Room.class));
        return modelMapper.map(room, RoomDTO.class);
    }

    public void updateRoom(RoomDTO room) {
        roomRepository.save(modelMapper.map(room, Room.class));
    }
}
