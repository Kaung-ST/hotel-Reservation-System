package com.example.hotelbookingassignment.config;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class securityConfig {
    private final UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=
                new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception{
        http.formLogin(c ->{
            c.loginPage("/login")
                    .failureUrl("/login-error")
                    .permitAll();
        });
        http.logout( c -> {
            c.logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll();
        });

        http.authorizeHttpRequests( a -> {
            a.requestMatchers("/bootstrap/**","/images/**", "/","/home/**","/date/**","/room-list/**","/register-room/**","/auth/**","/room-register","/guest-detail/**")
                    .permitAll()
                    .requestMatchers("/room/**").hasRole("ADMIN")
                    .anyRequest().authenticated();
        });
        http.csrf( c -> c.disable());
        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        var uds=new InMemoryUserDetailsManager();
        var john= User.withUsername("john")
                .password("john")
                .roles("ADMIN").build();
        uds.createUser(john);
        return uds;
    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//        http.formLogin(c -> c.loginPage("/login")
//                .permitAll());
//        http.logout(c -> c.logoutUrl("/logout")
//                .logoutSuccessUrl("/login").permitAll())
//        http.authorizeHttpRequests(c->
//                c.requestMatchers("/bootstrap/**","/images/**", "/","/home/**","/date/**","/room-list/**").permitAll()
//                        .requestMatchers("/room/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//
//        );
//        http.csrf(c -> c.disable());
//        return http.build();
//    }
//    @Bean
//    public UserDetailsService userDetailsService(){
//        var uds=new InMemoryUserDetailsManager();
//        var john= User.withUsername("john")
//                .password("john")
//                .roles("ADMIN").build();
//            uds.createUser(john);
//            return uds;
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//     return NoOpPasswordEncoder.getInstance();
//    }
}
