package com.example.vertical_slice_architecture.user.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "usr_id")
    private UUID id;

    @Column(name = "usr_name", nullable = false)
    private String name;

    @Column(name = "usr_email", nullable = false, length = 128)
    private String email;

    @Column(name = "usr_password", nullable = false, length = 128)
    private String password;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
