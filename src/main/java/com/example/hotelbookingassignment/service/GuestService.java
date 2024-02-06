package com.example.hotelbookingassignment.service;

import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.repository.GuestRepository;
import com.example.hotelbookingassignment.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GuestService {
    private final GuestRepository guestRepository;
    public UUID search(String email){
        Optional<Guest> guestOptional = guestRepository.findGuestByEmail(email);
        if (guestOptional.isPresent()) {
            Guest guest = guestOptional.get();
            System.out.println("Guest Id " + guest.getId());
            return guest.getId();
        } else {
            System.out.println("No guest found with the email nono@gmail");
            return null;
        }
    }

}
