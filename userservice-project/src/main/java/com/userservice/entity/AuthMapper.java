package com.userservice.entity;

import com.userservice.dto.LoginResponseDto;
import org.springframework.stereotype.Component;

@Component
public class AuthMapper {

    public LoginResponseDto toLoginResponse(User user,String token){
    return  LoginResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .tokentype("Bearer")
                .token(token)
                .build();
    }
}
