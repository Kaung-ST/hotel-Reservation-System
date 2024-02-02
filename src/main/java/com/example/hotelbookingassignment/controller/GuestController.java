package com.example.hotelbookingassignment.controller;

import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.ds.Reservation;
import com.example.hotelbookingassignment.repository.GuestRepository;
import com.example.hotelbookingassignment.repository.ReservationRepository;
import com.example.hotelbookingassignment.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class GuestController {
    private final AuthService authService;
    private final GuestRegistrationService guestRegistrationService;
    private final ApplicationService applicationService;
    private final GuestRepository guestRepository;
    private final BookingService bookingService;
    private final GuestService guestService;
    private final ReservationRepository reservationRepository;

    @GetMapping("/auth/register-room")
    public String createReserve(@RequestParam("name")String name,
                                @RequestParam("date")LocalDate date,
                                Model model){
        model.addAttribute("guest",new Guest());
        model.addAttribute("roomName",name);
        model.addAttribute("getDate",date);
        return "guestRegister";
    }
    @GetMapping("/auth/register-room-auth")
    public String createRes(
            @RequestParam("name")String name,
                                @RequestParam("email")String email,
                                @RequestParam("date")LocalDate date,
                                Model model){
        model.addAttribute("guest",new Guest());
        model.addAttribute("roomName",name);
        model.addAttribute("getDate",date);
        model.addAttribute("email",email);
        return "guestRegister";
    }
    @PostMapping("/auth/room-register")
    public String Registration(@RequestParam String name,
                               @RequestParam("fname")String fname,
                               @RequestParam("lname")String lname,
                               @RequestParam("email")String email,
                               @RequestParam("pass")String pass,
            @RequestParam("date")LocalDate date,
            Model model){
        Guest guest =new Guest(fname,lname);
        guest.setEmail(email);
        guest.setPassword(pass);
        model.addAttribute("roomName",name);
        authService.register(guest);
        applicationService.bookSpecificRoomForRegisteredGuest(guest,name,date);
        return "redirect:/login";
    }
    @PostMapping("/auth/reserve-room")
    public String bookNow(@RequestParam("name")String name,
                          @RequestParam("date")LocalDate date,
                          @RequestParam("email")String email, Model model){

        guestRepository.findGuestByEmail(email);
        System.out.println(email);
        Optional<Guest> guestOptional = guestRepository.findGuestByEmail(email);
        if (guestOptional.isPresent()) {
            Guest guest = guestOptional.get();
            System.out.println("First Name: " + guest.getFirstName());
            System.out.println("Last Name: " + guest.getLastName());
           // Guest guestUser=new Guest(guest.getFirstName(),guest.getLastName());
            applicationService.bookSpecificRoomForRegisteredGuest(guest,name,date);
        } else {
            System.out.println("No guest found with the email nono@gmail");
        }

        return  "redirect:/guest-detail?email=" + email;
    }
    @GetMapping("/auth/cancel")
    public String deleteReservation(@RequestParam("id")UUID id,@RequestParam("email") String email){
        reservationRepository.deleteById(id);
        return "redirect:/guest-detail?email=" + email;
    }
    @GetMapping("/guest-detail")
    public String seeDetail(
                            @RequestParam("email")String email,
                            Model model) {
        Set<Reservation>reservations=bookingService.UserRoomDetail(guestService.search(email));
        model.addAttribute("reservations",reservations);
        model.addAttribute("errorMessage","not found");
        return "guestDetailForm";

    }
    @GetMapping("/guest-detail/history")
    public String detailHistory( @RequestParam("email")String email,
                                 Model model){
        Set<Reservation>reservations=reservationRepository.findByGuest_Id(guestService.search(email));
        model.addAttribute("reservations",reservations);
        model.addAttribute("errorMessage","not found");
        return "historyForm";
    }

    public String createBookSpecificRoom(@RequestParam("name")String name,
                                         @RequestParam("email")String email,
                                         @RequestParam("date")LocalDate date){

        return "specificRoomForm";
    }
}
