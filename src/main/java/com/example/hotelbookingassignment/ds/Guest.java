package com.example.hotelbookingassignment.ds;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.validation.annotation.Validated;


import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Guest {
    @Id
    @GeneratedValue
    private UUID id;
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles=
            new HashSet<>();

    public void addRole(Role role){
        role.getGuests().add(this);
        this.roles.add(role);
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Guest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;

    }
}
