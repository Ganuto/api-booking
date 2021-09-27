package com.booking.api.controller;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.mock.UserMock;
import com.booking.api.service.UserService;
import com.booking.api.util.TestUtils;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void createUserSuccessfully() throws Exception {
        UserCreationResponse userCreationResponse = UserMock.createUserCreationResponse();
        UserCreationRequest userCreationRequest = UserMock.createUserCreationRequest();

        when(userService.create(any(UserCreationRequest.class))).thenReturn(userCreationResponse);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(userCreationRequest)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userId").value(userCreationResponse.getUserId()))
                .andExpect(jsonPath("$.name").value(userCreationResponse.getName()))
                .andExpect(jsonPath("$.document").value(userCreationResponse.getDocument()))
                .andExpect(jsonPath("$.login").value(userCreationResponse.getLogin()));
    }

    @Test
    public void createUserAndReturnUnprocessableEntity() throws Exception {
        UserCreationRequest userCreationRequest = UserMock.createUserCreationRequest();

        when(userService.create(any(UserCreationRequest.class))).thenThrow(DataIntegrityViolationException.class);

        mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.convertObjectToJsonString(userCreationRequest)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
}
