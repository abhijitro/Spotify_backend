package com.cts.spotify.userprofile.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name="user_details")
@Entity
public class UserDetails  {

    @Id
    private Integer id;

    @Column(unique = true)
    private  String username;
    private String password;
    private  String role;
}
