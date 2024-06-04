package com.cts.spotify.userprofile.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {

    @Id
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    @JsonProperty("phone")
    private long number;
    private Date dateOfBirth;


}
