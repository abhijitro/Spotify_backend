
  package com.cts.spotify.userprofile.service;
  
  import org.junit.jupiter.api.AfterEach; import
  org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import
  org.mockito.Mock; import org.modelmapper.ModelMapper; import
  org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.boot.test.mock.mockito.MockBean;
  
  import com.cts.spotify.userprofile.dto.UserProfileDto; import
  com.cts.spotify.userprofile.model.UserProfile; import
  com.cts.spotify.userprofile.repository.UserProfileRepository;
  
  import static org.junit.jupiter.api.Assertions.*; import static
  org.mockito.Mockito.when;
  
  import java.text.ParseException; import java.text.SimpleDateFormat; import
  java.util.ArrayList; import java.util.Arrays; import java.util.List;
  
  class UserProfileServiceImplTest {
  
  @MockBean 
  private UserProfileRepository userRepo;
  
  @Autowired 
  private final ModelMapper modelMapper=new ModelMapper();
  
  @Autowired 
  private UserProfileService userService;
  
  @MockBean 
  private UserProfile userProfile; 
  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
  List<UserProfile> userProfiles=new  ArrayList<>();
  
  @BeforeEach void setUp() throws ParseException { 
	  userProfiles = Arrays.asList
  (new UserProfile( "johndoe", "john.doe@example.com", "password123", "John",
  "Doe", 123456789L, null ), new UserProfile( "abhijitro", "abhi@test.com",
  "2210", "Abhijit", "Roy", 7003559802L, null ) ); 
	  }
  
  @AfterEach void tearDown(){ 
	  userProfiles.clear(); 
	  userRepo.deleteAll();
      userProfile=null; 
      }
  
  
  @Test 
  void getAllUsers() throws Exception { 
  List<UserProfileDto> expectedUsers=Arrays.asList( new
  UserProfileDto("abhijitro","Abhijit","Roy","abhi@test.com",7003559802L,dateFormat.parse("1997-05-07")), new
  UserProfileDto("johndoe","John","Doe","john@test.com",9903679034L,dateFormat.parse("1997-05-07")));
  when(userRepo.findAll()).thenReturn(userProfiles); 
  List<UserProfileDto> actualUsers=userService.getAllUsers();
  assertEquals(expectedUsers,actualUsers,"List of users should be equal"); }
  
  @Test 
  void getUserProfileById() throws Exception{ 
  String userName="abhijitro";
  UserProfileDto expectedUser=new UserProfileDto("abhijitro","Abhijit","Roy","abhi@test.com",7003559802L,dateFormat.parse("1997-05-07"));
  when(userRepo.findById(userName)).thenReturn(userProfiles.stream().filter(userProfile->userProfile
  .getUsername().equals(userName)).findFirst());
  UserProfileDto actualUser=userService.getUserProfileById(userName);
  assertEquals(expectedUser,actualUser,"Users should match"); }
  
  @Test void saveUserProfile() throws Exception { 
  UserProfile user=new UserProfile("abhijitro","abhi@test.com","2210"
  ,"Abhijit","Roy",7003559802L,dateFormat.parse("1997-05-07"));
  userService.saveUserProfile(user);
  UserProfileDto userDto=modelMapper.map(user,UserProfileDto.class); List<UserProfileDto>
  users=userService.getAllUsers();
  assertTrue(users.contains(userDto),"New user should be saved"); }
  
  @Test void updateUserProfile() throws Exception{ 
  String userName="abhijitro";
  UserProfileDto updatedUser=new UserProfileDto(userName,"Dholu","Kumar","dholu@test.com",990356789L,dateFormat.parse("1997-05-07"));
  when(userRepo.findById(userName)).thenReturn(userProfiles.stream()
  .filter(userProfile->userProfile.getUsername().equals(userName)).findFirst());
   userService.updateUserProfile(updatedUser, userName);
   List<UserProfileDto> users=userService.getAllUsers();
  assertTrue(users.contains(updatedUser),"User should be updated"); } }
 