package com.example.hotelbookingassignment.controller;

import com.example.hotelbookingassignment.ds.Room;
import com.example.hotelbookingassignment.repository.RoomRepository;
import com.example.hotelbookingassignment.service.ApplicationService;
import com.example.hotelbookingassignment.service.BookingService;
import com.example.hotelbookingassignment.service.GuestRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Controller
@RequiredArgsConstructor
public class UserRoomController {
    private final BookingService bookingService;
    private final RoomRepository roomRepository;
    private final ApplicationService applicationService;
    private final GuestRegistrationService guestRegistrationService;
    @GetMapping("/room-list")
    public String home(Model model, Room room){
        Set<Room> allRooms = roomRepository.findAll();
        model.addAttribute("rooms", allRooms);
        model.addAttribute("date",LocalDate.now());
        Map<String, String> roomStatusMap = bookingService.getStatusForRooms(allRooms);
        model.addAttribute("roomStatusMap", roomStatusMap);
        return "roomList";
    }
    @GetMapping("/room-list/date")
    public String roomAtDate(@RequestParam("date")LocalDate date,
            Room room,Model model){
        Set<Room> allRooms = roomRepository.findAll();
        model.addAttribute("rooms", allRooms);
        model.addAttribute("getDate",date);
        model.addAttribute("currentdate",LocalDate.now());
        Map<String, String> roomStatusMap = bookingService.getStatusForRoomDate(allRooms,date);
        model.addAttribute("roomStatusMap", roomStatusMap);
        return "roomList";

    }
}
