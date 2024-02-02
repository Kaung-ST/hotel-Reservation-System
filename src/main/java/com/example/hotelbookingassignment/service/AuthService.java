package com.example.hotelbookingassignment.service;

import com.example.hotelbookingassignment.ds.Guest;
import com.example.hotelbookingassignment.ds.Role;
import com.example.hotelbookingassignment.repository.GuestRepository;
import com.example.hotelbookingassignment.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final RoleRepository roleRepository;
    private final GuestRepository guestRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(Guest guest){
        Role role=roleRepository.findRoleByRoleName("ROLE_USER")
                .orElseThrow(EntityNotFoundException::new);
        guest.setPassword(passwordEncoder
                .encode(guest.getPassword()));
       guest.addRole(role);
       guestRepository.save(guest);
    }
}
