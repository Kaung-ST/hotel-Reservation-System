package com.example.hotelbookingassignment.controller;

import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.ds.Reservation;
import com.example.hotelbookingassignment.ds.Room;
import com.example.hotelbookingassignment.repository.ReservationRepository;
import com.example.hotelbookingassignment.repository.RoomRepository;
import com.example.hotelbookingassignment.service.ApplicationService;
import com.example.hotelbookingassignment.service.BookingService;
import com.example.hotelbookingassignment.service.GuestRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

@Controller
@RequiredArgsConstructor
public class RoomController {
    private final RoomRepository roomRepository;
    private final BookingService bookingService;
    private final ApplicationService applicationService;
    private final GuestRegistrationService guestRegistrationService;
    private final ReservationRepository reservationRepository;

@GetMapping("/room")
public String rooms(Model model) {
    Set<Room> allRooms = roomRepository.findAll();
    model.addAttribute("rooms", allRooms);
    model.addAttribute("currentDate",LocalDate.now());
    Map<String, String> roomStatusMap = bookingService.getStatusForRooms(allRooms);
    model.addAttribute("roomStatusMap", roomStatusMap);
    return "room";
}
    @GetMapping("/room/search-date")  //added room
    public String roomAtDate(@RequestParam("date")LocalDate date,
                             Room room,Model model){
        Set<Room> allRooms = roomRepository.findAll();
        model.addAttribute("rooms", allRooms);
        model.addAttribute("getDate",date);
        model.addAttribute("currentDate",LocalDate.now());
        Map<String, String> roomStatusMap = bookingService.getStatusForRoomDate(allRooms,date);
        model.addAttribute("roomStatusMap", roomStatusMap);
        return "room";
    }

    public String findAvaliableRoom(Model model){
        return "avaliableRooms";
    }
    @GetMapping("/room/delete")
    public String deleteRoom(@RequestParam("id")UUID id){
        roomRepository.deleteById(id);
        return "redirect:/room";
    }
    @GetMapping("/room/cancel")
    public String deleteReservation(@RequestParam("id")UUID id,
                                    @RequestParam("date")LocalDate date){
        reservationRepository.deleteByRoom_IdAndReservationDate(id,date);
        return "redirect:/room/search-date?date="+date;
    }
    @GetMapping("/room/room-save")
    public String createRoom(Model model){
        model.addAttribute("room",new Room());
      return "roomForm";
    }
    @PostMapping("/room/save")
    public String processRoom(@RequestParam("name")String name,
                              @RequestParam("section")String section){
        Room room=new Room(name,section);
        roomRepository.save(room);
        return "redirect:/room";
    }
    @GetMapping("/room/room-reserve")
    public String createReserve(@RequestParam("name")String name,
                                @RequestParam("date")LocalDate date,
                                Model model){
        model.addAttribute("guest",new Guest());
        model.addAttribute("roomName",name);
        model.addAttribute("getDate",date);
        return "ReserveForm";
    }
    // /room/room-reserve?name=Room5&fname=Kaung&lname=Thu&date=2024,02,01
    @PostMapping("/room/reserve")
    public String reserve(@RequestParam String name,
                          @RequestParam("fname")String fname,
                          @RequestParam("lname")String lname,
                          @RequestParam("date")LocalDate date,
                          Model model
                          ){
       model.addAttribute("roomName",name);
       Guest guest=new Guest(fname,lname);
       guestRegistrationService.registerGuest(guest);
       System.out.println("Guest Name : "+guest.getFirstName()+guest.getLastName());
       applicationService.bookSpecificRoomForRegisteredGuest(guest,name,date);

        return "redirect:/room/search-date?date="+date;
    }
    // room/detail?date=??&id=??
    @GetMapping("room/detail")
    public String seeDetail(@RequestParam("date") LocalDate date,
                            @RequestParam("id") UUID id,
                            Model model) {
        Optional<Reservation> reservation = bookingService.detail(date, id);
        if (reservation.isPresent()) {
            model.addAttribute("reservation", reservation);
        } else {
            model.addAttribute("errorMessage", "Reservation not found");
        }
        return "DetailForm";
    }

    @ModelAttribute("status")
    public Map<String, String> state() {
        Map<String, String> roomStatusMap = new HashMap<>();

        Set<Room> rooms = roomRepository.findAll();
        for (Room room : rooms) {
            String status = bookingService.isAvailable(room.getName());
            roomStatusMap.put(room.getName(), status);
        }

        return roomStatusMap;
    }

}
