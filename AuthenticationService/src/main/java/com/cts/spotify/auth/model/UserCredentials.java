package com.cts.spotify.auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCredentials {
    @Id
    private int userid;
    private String username;
    private String password;
    private String role;
}
