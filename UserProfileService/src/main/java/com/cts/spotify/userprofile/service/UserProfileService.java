package com.cts.spotify.userprofile.service;



import com.cts.spotify.userprofile.dto.UserProfileDto;
import com.cts.spotify.userprofile.model.UserProfile;
import com.cts.spotify.userprofile.repository.UserProfileRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public interface UserProfileService {
	
	List<UserProfileDto> getAllUsers();

    UserProfileDto getUserProfileById(String username);

    UserProfileDto saveUserProfile(UserProfile userProfile);

    UserProfileDto updateUserProfile(UserProfileDto userProfileDto, String username);

}
