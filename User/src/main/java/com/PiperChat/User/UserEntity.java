package com.PiperChat.User;

import com.PiperChat.User.role.Role;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String profilePicture;
    private String bio;
    private LocalDate dateBirth;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonManagedReference
    private Role role;
}