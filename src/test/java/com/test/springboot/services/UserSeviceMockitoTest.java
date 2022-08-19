package com.test.springboot.services;

import com.test.springboot.entity.User;
import com.test.springboot.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class UserSeviceMockitoTest {

    @InjectMocks // Allows to inject Mock Objects
    UserService userService;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void setUp() throws Exception {
        // Needed for Mockito to instantiate the Mock Objects and Inject into the userService object
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetById() {
        User existingUser = new User();
        Long testedUser = 1L;
        existingUser.setId(testedUser);
        existingUser.setName("Ali");
        existingUser.setEmail("ali.adamecantoran@hcl.com");
        existingUser.setBirthDate(LocalDate.of(1991,12,15));

        when(userRepository.findById(anyLong()))
                .thenReturn(Optional.of(existingUser));

        User userDto = userService.getById(testedUser);

        assertNotNull(userDto);
        assertEquals("ali.adamecantoran@hcl.com", userDto.getEmail());
    }

    /*@Test
    void delete() {
        fail("Not yet implemented");
    }*/

}
