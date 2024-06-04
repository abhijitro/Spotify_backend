package com.cts.spotify.userprofile.repository;


import com.cts.spotify.userprofile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, String> {

    boolean existsByEmail(String email);

    boolean existsByNumber(long number);

}
