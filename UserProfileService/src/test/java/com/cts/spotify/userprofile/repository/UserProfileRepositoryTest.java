package com.cts.spotify.userprofile.repository;

import com.cts.spotify.userprofile.model.UserProfile;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserProfileRepositoryTest {

    @Autowired
    private UserProfileRepository userProfileRepository;
    UserProfile userProfile;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @BeforeEach
    void setUp() throws ParseException {
        userProfile = new UserProfile(
                "johndoe",
                "john.doe@example.com",
                "password123",
                "John",
                "Doe",
                123456789L,
                dateFormat.parse("1990-01-01")
        );
        userProfileRepository.save(userProfile);
    }

    @AfterEach
    void tearDown(){
        userProfile = null;
        userProfileRepository.deleteAll();
    }

    @Test
    void testFindAll(){

        System.out.println(userProfileRepository.findAll());
        // Then
        assertEquals(1, userProfileRepository.findAll().size());
    }

    @Test
    void existsByEmail() {
        boolean existsByEmail = userProfileRepository.existsByEmail("john.doe@example.com");
        // Assert that the entity was found by email
        assertThat(existsByEmail).isTrue();
        
    }

    @Test
    void existsByNumber() {
    	boolean existsByNumber = userProfileRepository.existsByNumber(123456789L);
        // Assert that the entity was found by email
        assertThat(existsByNumber).isTrue();
    }
}