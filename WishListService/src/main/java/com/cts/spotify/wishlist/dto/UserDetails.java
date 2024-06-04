package com.cts.spotify.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetails {

    private  String username;
    private String password;
    private  String role;
}
