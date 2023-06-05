package com.ua.hotel.service;

import com.ua.hotel.Dto.GuestDTO;
import com.ua.hotel.domain.Guest;
import com.ua.hotel.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    @Autowired
    private ModelMapper modelMapper;

    public GuestDTO create(GuestDTO guestDTO) {

        Guest guest = guestRepository.save(modelMapper.map(guestDTO, Guest.class));
        return modelMapper.map(guest, GuestDTO.class);
    }

    public Optional<GuestDTO> updateGuest(GuestDTO guestDTO) {

        Guest  guest = null;

        Optional<Guest> guestOptional = guestRepository.findByPassportNumber(guestDTO.getPassportNumber());

        if (guestOptional.isPresent()) {
            guest = guestOptional.get();
            guest.setLastName(guestDTO.getLastName());
        } else {
            guestOptional = guestRepository.findByLastName(guestDTO.getLastName());

            if (guestOptional.isPresent()) {
                guest = guestOptional.get();
                guest.setPassportNumber(guestDTO.getPassportNumber());
            }
        }

        if(!guestOptional.isPresent()) {
            return Optional.empty();
        }

        guest.setContact(guestDTO.getContact());
        guest.setFirstName(guestDTO.getFirstName());

        guestDTO = modelMapper.map(guestRepository.save(guest), GuestDTO.class);
        return Optional.ofNullable(guestDTO);
    }

    public Optional<GuestDTO> getByLastName(String lastName) {

        return Optional.ofNullable(modelMapper.map(guestRepository.findByLastName(lastName), GuestDTO.class));
    }

    public Optional<GuestDTO> getByPassportNumber(String passportNumber) {

        Optional<Guest> guest = guestRepository.findByPassportNumber(passportNumber);

        return Optional.ofNullable(modelMapper.map(guest, GuestDTO.class));
    }
}

