package com.example.hotelbookingassignment.repository;

import com.example.hotelbookingassignment.ds.Reservation;
import com.example.hotelbookingassignment.ds.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<Reservation, UUID> {

    Set<Reservation> findAllByReservationDate(LocalDate reservationDate);

    boolean existsByRoomAndReservationDate(Room room, LocalDate reservationDate);
    Set<Reservation> findByRoomId(UUID id);
    Set<Reservation>findByGuest_Id(UUID id);
    @Transactional
    Optional<Reservation>deleteByRoom_IdAndReservationDate(UUID id,LocalDate date);


    Optional<Reservation> findByReservationDateAndRoom_Id(LocalDate reservationDate,UUID id);
  //  Optional<Reservation> findByGuest_IdAndAndReservationDateAfter(UUID id, LocalDate reservationDate);
    Set<Reservation> findByGuest_IdAndAndReservationDateAfter(UUID id, LocalDate reservationDate);
}
