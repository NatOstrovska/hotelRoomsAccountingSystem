package com.ua.hotel.repository;

import com.ua.hotel.domain.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    Optional<Guest> findByLastName(String lastName);

    Optional<Guest> findByPassportNumber(String passportNumber);
}
