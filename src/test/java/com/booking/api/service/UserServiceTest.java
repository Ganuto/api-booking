package com.booking.api.service;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.domain.User;
import com.booking.api.mock.UserMock;
import com.booking.api.repository.UserRepository;
import com.booking.api.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class UserServiceTest {

    @MockBean
    private UserRepository userRepository;

    @SpyBean
    private UserServiceImpl userService;

    @Test
    public void createUserSuccessfully() {
        UserCreationRequest userCreationRequest = UserMock.createUserCreationRequest();
        User user = UserMock.createUser();

        when(userRepository.save(any(User.class))).thenReturn(user);

        UserCreationResponse userCreationResponse = userService.create(userCreationRequest);

        assertEquals(user.getId(), userCreationResponse.getUserId());
        assertEquals(user.getDocument(), userCreationResponse.getDocument());
        assertEquals(user.getLogin(), userCreationResponse.getLogin());
        assertEquals(user.getName(), userCreationResponse.getName());
    }

    @Test
    public void createUserAndReturnDataIntegrityViolationException() {
        UserCreationRequest userCreationRequest = UserMock.createUserCreationRequest();

        when(userRepository.save(any(User.class))).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> userService.create(userCreationRequest));
    }
}

