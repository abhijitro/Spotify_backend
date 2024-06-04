
  package com.cts.spotify.userprofile.controller;
  
  import org.junit.jupiter.api.Test; import
  org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
  import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
  import org.springframework.boot.test.context.SpringBootTest; import
  org.springframework.boot.test.mock.mockito.MockBean; import
  org.springframework.http.HttpStatus; import
  org.springframework.http.MediaType; import
  org.springframework.http.ResponseEntity; import
  org.springframework.test.web.servlet.MockMvc; import static
  org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; import
  static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
  import com.cts.spotify.userprofile.dto.UserProfileDto; import
  com.cts.spotify.userprofile.model.UserProfile; import
  com.cts.spotify.userprofile.repository.UserProfileRepository; import
  com.cts.spotify.userprofile.service.UserProfileService; import
  com.fasterxml.jackson.databind.ObjectMapper; import
  com.google.common.base.Optional;
  
  import static org.junit.jupiter.api.Assertions.*; import static
  org.mockito.Mockito.when;
  
  import java.util.ArrayList; import java.util.Arrays; import java.util.List;
  
  @SpringBootTest
  @AutoConfigureMockMvc 
  class UserProfileControllerTest {
  
  @Autowired 
  private MockMvc mockMvc;
  
  @MockBean 
  private UserProfileRepository userRepo;
  
  @MockBean 
  private UserProfileService userService;
  
  @Autowired 
  private ObjectMapper objectMapper;
  
  @Test 
  void getAllUsers() throws Exception{ 
  String token="Bearer test-token";
  List<UserProfileDto> expectedUsers=Arrays.asList( new
  UserProfileDto("abhijitro","Abhijit","Roy","abhi@test.com",7003559802L,null), new
  UserProfileDto("buro","Labby","Buro","labby@test.com",9903679034L,null) );
  when(userService.getAllUsers()).thenReturn(expectedUsers);
  
  mockMvc.perform(get("/getAllUser") .header("Authorization",token)
  .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  .andExpect(content().json(objectMapper.writeValueAsString(expectedUsers))); }
  
  @Test void getUserProfileById() throws Exception{ 
  String token ="Bearer test-token"; 
  String authToken = token.substring(7);
  UserProfileDto userProfile=new UserProfileDto("abhijitro","Abhijit","Roy","abhi@test.com",7003559802L,null);
  when(userService.getUserProfileById(userProfile.getUsername())).thenReturn(
  userProfile);
  
  mockMvc.perform(get("/getUserById/{username}") .header("Authorization",token)
  .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  .andExpect(content().json(objectMapper.writeValueAsString(userProfile))); }
  
  @Test void saveUserProfile() throws Exception { 
  UserProfile userProfile = new UserProfile("abhijitro","abhi@test.com","2210","Abhijit","Roy",7003559802L,
  null); 
  UserProfileDto userProfileDto=new UserProfileDto(userProfile.getUsername(),userProfile.getFirstName(),
  userProfile.getLastName(),userProfile.getEmail(),userProfile.getNumber(),null); 
  // Mock the service call
  when(userService.saveUserProfile(userProfile)).thenReturn(userProfileDto); //
  //Perform the MockMvc request with an authenticated user
  mockMvc.perform(post("/addUser") .contentType(MediaType.APPLICATION_JSON)
  .content(objectMapper.writeValueAsString(userProfile)))
  .andExpect(status().isOk())
  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  .andExpect(content().json(objectMapper.writeValueAsString(userProfile)));
  
  }
  
  @Test void updateUserProfile() throws Exception{ 
  String token = "Bearer test-token"; 
  UserProfileDto userProfile=new UserProfileDto("abhijitro","Abhijit","Roy","abhi@test.com",7003559802L,null);
  when(userService.updateUserProfile(userProfile,userProfile.getUsername())).
  thenReturn(userProfile); // Perform the MockMvc request with an authenticated user
  mockMvc.perform(put("/update/{username}") .header("Authorization",token)
  .contentType(MediaType.APPLICATION_JSON)
  .content(objectMapper.writeValueAsString(userProfile)))
  .andExpect(status().isOk())
  .andExpect(content().contentType(MediaType.APPLICATION_JSON))
  .andExpect(content().json(objectMapper.writeValueAsString(userProfile))); } }
 