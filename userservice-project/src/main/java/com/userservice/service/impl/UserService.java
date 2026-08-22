package com.userservice.service.impl;

import com.userservice.dto.LoginRequestDto;
import com.userservice.dto.LoginResponseDto;
import com.userservice.dto.UserCreateRequestDto;
import com.userservice.dto.UserResponseDto;

public interface UserService {

    UserResponseDto register(UserCreateRequestDto requestDto);

    LoginResponseDto login(LoginRequestDto loginRequestDto);

    UserResponseDto getById(long id);


}
