package com.example.eatgo.application;

import com.example.eatgo.domain.CategoryRepository;
import com.example.eatgo.domain.User;
import com.example.eatgo.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(
                User.builder()
                        .email("tester@example.com")
                        .name("테스터")
                        .level(1L)
                        .build()
        );

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertEquals("테스터", user.getName());
    }

    @Test
    public void addUser() {
        String email = "admin@example.com";
        String name = "Administrator";

        User mockUser = User.builder().email(email).name(name).build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(email, name);

        assertEquals(name, user.getName());
    }

    @Test
    public void updateUser() {
        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Superman";
        Long level = 100L;

        User mockUser = User.builder().id(id).email(email).name("Administrator").level(1L).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.updateUser(id, email, name, level);

        verify(userRepository).findById(eq(id));

        assertEquals("Superman", user.getName());
        assertEquals(true, user.isAdmin());

    }

    @Test
    public void deactiveUser() {
        Long id = 1004L;
        String email = "admin@example.com";
        String name = "Administrator";
        Long level = 100L;

        User mockUser = User.builder().id(id).email("admin@example.com").name("Administrator").level(100L).build();
        given(userRepository.findById(id)).willReturn(Optional.of(mockUser));

        User user = userService.deactiveUser(1004L);

        verify(userRepository).findById(1004L);

        assertEquals(false, user.isAdmin());
        assertEquals(false, user.isActive());
    }
}