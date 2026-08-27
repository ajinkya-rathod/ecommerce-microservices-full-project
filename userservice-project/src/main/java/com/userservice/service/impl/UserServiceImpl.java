package com.userservice.service.impl;

import com.userservice.dto.LoginRequestDto;
import com.userservice.dto.LoginResponseDto;
import com.userservice.dto.UserCreateRequestDto;
import com.userservice.dto.UserResponseDto;
import com.userservice.entity.AuthMapper;
import com.userservice.entity.User;
import com.userservice.exception.ResourceAlreadyExistException;
import com.userservice.exception.ResourceNotFoundException;
import com.userservice.mapper.UserMapper;
import com.userservice.repo.UserRepository;
import com.userservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthMapper authMapper;


    @Override
    public UserResponseDto register(UserCreateRequestDto requestDto) {

        if(userRepository.existByEmail(requestDto.getEmail())){
            throw new ResourceAlreadyExistException("email Already exist");
        }

        User user = mapper.toEntity(requestDto);
        User savedUser = userRepository.save(user);
        return mapper.toDto(savedUser);

    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {

        User user = userRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(()-> new ResourceNotFoundException("invalid credential"));

      boolean mathes = passwordEncoder.matches(loginRequestDto.getPassword(), user.getPassword());

      if(!mathes) {
          throw new ResourceNotFoundException("invalid credential");
      }


          String token = jwtUtil.generateToken(user.getId(),user.getEmail(),user.getRoles());

          return authMapper.toLoginResponse(user,token);


    }

    @Override
    public UserResponseDto getById(long id) {

        User user = userRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("user not found with this id "+id));

       return mapper.toDto(user);
    }
}
