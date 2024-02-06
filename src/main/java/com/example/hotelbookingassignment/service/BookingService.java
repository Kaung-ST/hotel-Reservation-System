package com.example.hotelbookingassignment.service;

import com.example.hotelbookingassignment.ds.BookingResult;
import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.ds.Reservation;
import com.example.hotelbookingassignment.ds.Room;
import com.example.hotelbookingassignment.repository.ReservationRepository;
import com.example.hotelbookingassignment.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RoomRepository roomRepository;

    public Optional<Room> findAvailableRoom(LocalDate date) {
        return roomRepository.findAll().stream()
                .filter(room -> isRoomAvailableAtDate(room, date))
                .findFirst();
    }
    public Set<Room> findAvailableRooms(LocalDate date) {
        return roomRepository.findAll().stream()
                .filter(room -> isRoomAvailableAtDate(room, date))
                .collect(Collectors.toSet());
    }
    public Optional<Reservation> bookRoom(String roomName, Guest guest, LocalDate date) {
        Optional<Room> room = roomRepository.findByName(roomName);
        return room.map(r -> bookRoom(r, guest, date))
                .orElse(BookingResult.createNoRoomAvailableResult().getReservation());

    }
    public Optional<Reservation> bookRoom(Room room, Guest guest, LocalDate date) {
        if (isRoomAvailableAtDate(room, date)) {
            Reservation reservation = new Reservation(room, guest, date);
            reservationRepository.save(reservation);
            return BookingResult.createRoomBookedResult(reservation).getReservation();
        } else {
            return BookingResult.createNoRoomAvailableResult().getReservation();
        }
    }
    private boolean isRoomAvailableAtDate(Room room, LocalDate date) {
        boolean isAvailable = !reservationRepository.existsByRoomAndReservationDate(room, date);

        if (!isAvailable) {
            System.out.println("Room " + room.getName() + " is not available on " + date);
        }

        return isAvailable;
    }
    public String isAvailable(String name) {
        Optional<Room> optionalRoom = roomRepository.findByName(name);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            boolean isAvailable = !reservationRepository.existsByRoomAndReservationDate(room, LocalDate.now());
            if (!isAvailable) {
                return "Booked";
            }
        }
        return "Available";
    }
    public Map<String, String> getStatusForRooms(Set<Room> rooms) {
        Map<String, String> roomStatusMap = new HashMap<>();
        for (Room room : rooms) {
            boolean isAvailable = !reservationRepository.existsByRoomAndReservationDate(room, LocalDate.now());
            roomStatusMap.put(room.getName(), isAvailable ? "Available" : "Booked");
        }

        return roomStatusMap;
    }
    public Map<String, String> getStatusForRoomDate(Set<Room> rooms,LocalDate date) {
        Map<String, String> roomStatusMap = new HashMap<>();

        for (Room room : rooms) {
            boolean isAvailable = !reservationRepository.existsByRoomAndReservationDate(room,date);
            roomStatusMap.put(room.getName(), isAvailable ? "Available" : "Booked");
        }

        return roomStatusMap;
    }
    public Optional<Reservation> detail(LocalDate date ,UUID id){
       return reservationRepository.findByReservationDateAndRoom_Id(date,id);
    }
    public  Set<Reservation>UserRoomDetail(UUID id){
        return reservationRepository.findByGuest_IdAndAndReservationDateAfter(id,LocalDate.now());
    }

}
