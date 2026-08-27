package com.userservice.mapper;

import com.userservice.dto.UserCreateRequestDto;
import com.userservice.dto.UserResponseDto;
import com.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;


    // Convert dto to entity

    public User toEntity(UserCreateRequestDto requestDto){

     return User.builder().name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .phone(requestDto.getPhone())
                .roles("ROLE_USER")
                .build();

    }

    // convert ENTITY TO DTO

    public UserResponseDto toDto(User user){
       return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getName())
                .password(user.getPassword())
                .phone(user.getPhone())
                .roles(user.getRoles())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();

    }
}
