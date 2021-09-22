package com.booking.api.service.impl;

import com.booking.api.controller.data.request.UserCreationRequest;
import com.booking.api.controller.data.response.UserCreationResponse;
import com.booking.api.domain.User;
import com.booking.api.mapper.UserMapper;
import com.booking.api.repository.UserRepository;
import com.booking.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserCreationResponse create(UserCreationRequest userCreationRequest) {
        User user = userRepository.save(UserMapper.toDomain(userCreationRequest));
        return UserMapper.toResponse(user);
    }

    @Override
    public User findById(Long id) {
        return Optional.of(userRepository.findById(id)).get()
                .orElse(null);
    }
}
