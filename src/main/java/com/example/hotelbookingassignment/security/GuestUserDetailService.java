package com.example.hotelbookingassignment.security;

import com.example.hotelbookingassignment.repository.GuestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GuestUserDetailService implements UserDetailsService {
    private final GuestRepository guestRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Login UserName::::======="+ email);
        return guestRepository
                .findGuestByEmail(email)
                .map(SecurityUser::new)
                .orElseThrow(()->new UsernameNotFoundException("Error!"));

    }
}
