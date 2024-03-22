package com.cts.spotify.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userid;

    @Column(unique = true)
    private  String username;
    private String password;
    private  String role;
}
