package com.example.hotelbookingassignment.service;

import com.example.hotelbookingassignment.ds.BookingResult;
import com.example.hotelbookingassignment.ds.Guest;


import com.example.hotelbookingassignment.ds.Reservation;
import com.example.hotelbookingassignment.ds.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;


@Service
public class ApplicationService {

    @Autowired
    private GuestRegistrationService guestRegistrationService;
    @Autowired
    private BookingService bookingService;

    public BookingResult bookAnyRoomForNewGuest(String firstName, String lastName, LocalDate date) {
        Guest guest=registerGuest(firstName,lastName);
        return bookAnyRoomForRegisteredGuest(guest,date);

    }

    public Guest registerGuest(String firstName, String lastName) {
        Guest guest=new Guest(firstName,lastName);
        return guestRegistrationService.registerGuest(guest);
    }

    public BookingResult bookAnyRoomForRegisteredGuest(Guest guest,LocalDate date) {
        Optional<Room> availableRoom = bookingService.findAvailableRoom(date);
        return availableRoom.map(room -> {
            Optional<Reservation> reservation = bookingService.bookRoom(room, guest, date);
            return reservation.map(BookingResult::createRoomBookedResult)
                    .orElse(BookingResult.createNoRoomAvailableResult());
        }).orElse(BookingResult.createNoRoomAvailableResult());
    }

    public BookingResult bookSpecificRoomForRegisteredGuest(Guest guest, String roomName, LocalDate date) {
        Optional<Reservation> reservation = bookingService.bookRoom(roomName, guest, date);
        return reservation.map(BookingResult::createRoomBookedResult)
                .orElse(BookingResult.createNoRoomAvailableResult());
    }


}
