package com.example.eatgo.application;

import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @MockBean
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser() {
        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";
        userService.registerUser(email, name, password);

        verify(userRepository).save(any());
    }

    //@Test(expected = EmailExistedException.class)
    @Test
    public void registerUserWithExistedEmail() {

        String email = "tester@example.com";
        String name = "Tester";
        String password = "test";

        User user = User.builder().build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(user));

        userService.registerUser(email, name, password);

        verify(userRepository, never()).save(any());
    }

    @Test
    public void authenticateWithValidAttributes() {
        String email = "tester@example.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate(email, password);

        assertEquals(email, user.getEmail());
    }

//    @Test(expected = EmailNotExistedException.class)
//    public void authenticateWithNotvalidAttributes() {
//        String email = "x@example.com";
//        String password = "test";
//
//        User mockUser = User.builder()
//                .email(email)
//                .build();
//
//        given(userRepository.findByEmail(email)).willReturn(Optional.empty());
//
//        User user = userService.authenticate(email, password);
//    }

//    @Test(expected = PasswordWrongException.class)
//    public void authenticateWithWrongPassword() {
//        String email = "tester@example.com";
//        String password = "x";
//
//        User mockUser = User.builder()
//                .email(email)
//                .build();
//
//
//        given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
//
//        given(passwordEncoder.matches(any(), any())).willReturn(false);
//
//
//        User user = userService.authenticate(email, password);
//    }

}