package com.example.hotelbookingassignment;

import com.example.hotelbookingassignment.ds.*;
import com.example.hotelbookingassignment.repository.GuestRepository;
import com.example.hotelbookingassignment.repository.ReservationRepository;
import com.example.hotelbookingassignment.repository.RoleRepository;
import com.example.hotelbookingassignment.repository.RoomRepository;
import com.example.hotelbookingassignment.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@SpringBootApplication
@RequiredArgsConstructor
public class HotelBookingAssignmentApplication {
//    private final GuestRegistrationService guestRegistrationService;
//    private final BookingService bookingService;
//    private final RoomRepository roomRepository;
//    private  final ReservationRepository reservationRepository;
//    private final ApplicationService applicationService;
//    private final RoleRepository roleRepository;
//    private final AuthService authService;
//    private final GuestRepository guestRepository;
//    private final GuestService guestService;

    public static void main(String[] args) {
        SpringApplication.run(HotelBookingAssignmentApplication.class, args);
    }
    @Bean
    @Transactional @Profile("dev")
    public ApplicationRunner runner() {


     return r ->{

//         System.out.println("Search Guest id: "+ guestService.search("maung@gmail.com"));
//        Guest guest=new Guest("Admin","Lynn");
//        guest.setEmail("admin");
//        guest.setPassword("admin");
//        guestRepository.save(guest);

//         Optional<Guest> guestOptional = guestRepository.findGuestByEmail("nono@gmail");
//         if (guestOptional.isPresent()) {
//             Guest guest = guestOptional.get();
//             System.out.println("First Name: " + guest.getFirstName());
//             System.out.println("Last Name: " + guest.getLastName());
//         } else {
//             System.out.println("No guest found with the email nono@gmail");
   //      }
//         Guest guest=new Guest("test2","uu");
//         guest.setEmail("kkt@gmail.com");
//         guest.setPassword("pass");
//         authService.register(guest);
//         System.out.println("result"+guest);

     };
//            Role admin=new Role();
//            admin.setRoleName("ROLE_ADMIN");
//
//            Role user=new Role();
//            user.setRoleName("ROLE_USER");
//
//            roleRepository.save(admin);
//            roleRepository.save(user);
//        };
//        return r->{
//           Room room1=new Room("Room1","1");
//           Room room2=new Room("Room2","1");
//           Room room3=new Room("Room3","1");
//           Room room5=new Room("Room5","2");
//           roomRepository.save(room5);
//            roomRepository.save(room1);
//            roomRepository.save(room2);
//            roomRepository.save(room3);
//             Guest g2=new Guest("Impact","Hokai");
//             guestRegistrationService.registerGuest(g2);
//              bookingService.bookRoom(room1,g2, LocalDate.now());
//        };
    }
    @Bean
    @Transactional @Profile("dev")
    public ApplicationRunner runner2(){
        return r->{

            System.out.println();
//            //System.out.println("Detail: "+bookingService.detail());
////            UUID id=0x2652748E3618458B959B99EC15FE00CF;
//            System.out.println("Detail: "+ reservationRepository.findAllByReservationDate(LocalDate.now()));
//            Set<Room> rooms=roomRepository.findAll();
//            for(Room room:rooms){
//                System.out.println("Detail: by roomid: "+ reservationRepository.findByReservationDateAndRoom_Id(LocalDate.now(),room.getId()));
//            }
//            Iterable<Guest> guests=  guestRepository.findAll();
//            for(Guest guest:guests){
//                System.out.println("Search guest id::"+guest.getId());
//                System.out.println("Rev Guest Detail::"+
//                bookingService.UserRoomDetail(guest.getId()));
//                System.out.println("Reve Guest detail::"+
//                        reservationRepository.findByGuest_Id(guest.getId()));
//            }

           // System.out.println("Detail:Get Data:  "+ reservationRepository.findByReservationDateAndRoom_Id(LocalDate.now(),UUID.fromString("372135372C96416591DAA3235565D023")));


//          Room room6=new Room("Room4","2");
//          roomRepository.save(room6);
//            System.out.println("Start ::finding.....");
//
//            System.out.println("Result: "+ bookingService.isAvailable("Room2"));
//            System.out.println("Room Name:"+roomRepository.findByName("Room1"));
//            System.out.println("Test: "+bookingService.isAvailable("Room5"));
//            Set<Room> rooms=roomRepository.findAll();
//            for(Room room:rooms){
//                System.out.println("-------------------");
//                System.out.println("Room Name: "+ room.getName());
//                System.out.println("Room State: "+ bookingService.isAvailable(room.getName()));
//            }
        // Guest g2=applicationService.registerGuest("Yuu","Ko");
         // applicationService.bookAnyRoomForRegisteredGuest(g2,LocalDate.now());
//         System.out.println("Booking::"+  applicationService.bookAnyRoomForNewGuest("Kaung","Set",LocalDate.of(2024,01,23)));
           // applicationService.bookSpecificRoomForRegisteredGuest(g2,"Room2",LocalDate.of(2024,02,01));
        };
    }
}
