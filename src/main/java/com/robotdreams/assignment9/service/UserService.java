package com.robotdreams.assignment9.service;

import com.robotdreams.assignment9.dto.UserRequestDto;
import com.robotdreams.assignment9.entity.User;
import com.robotdreams.assignment9.repository.UserRepository;
import com.robotdreams.assignment9.service.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public Optional<User> findById(long  id){
        return userRepository.findById(id);
    }

    public boolean save(UserRequestDto userRequestDto) {

        User user = userMapper.userRequestDtoToUser(userRequestDto);
        userRepository.save(user);

        return user.getId() > 0;
    }
}
