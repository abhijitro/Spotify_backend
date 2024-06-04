package com.cts.spotify.userprofile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {

        private String username;
        private String firstName;
        private String lastName;
        private String email;
        @JsonProperty("phone")
        private long number;
        private Date dateOfBirth;
    }

