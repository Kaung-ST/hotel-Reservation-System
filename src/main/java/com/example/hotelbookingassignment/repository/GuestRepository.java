package com.example.hotelbookingassignment.repository;

import com.example.hotelbookingassignment.ds.Guest;
import io.micrometer.observation.ObservationFilter;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface GuestRepository extends CrudRepository<Guest, UUID> {
    Optional<Guest>findGuestByEmail(String email);

}
