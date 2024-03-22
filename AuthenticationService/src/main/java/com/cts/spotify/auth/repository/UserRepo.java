package com.cts.spotify.auth.repository;

import com.cts.spotify.auth.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<UserDetails,Integer> {
    UserDetails findByUsername(String username);
}
