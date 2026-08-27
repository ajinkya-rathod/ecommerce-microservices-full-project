package com.userservice.controller;

import com.userservice.dto.LoginRequestDto;
import com.userservice.dto.LoginResponseDto;
import com.userservice.dto.UserCreateRequestDto;
import com.userservice.dto.UserResponseDto;
import com.userservice.mapper.UserMapper;
import com.userservice.service.impl.UserService;
import jakarta.validation.Valid;
import jakarta.ws.rs.POST;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

   private final UserService userService;
   private final UserMapper mapper;

   @PostMapping("/register")
   public ResponseEntity<UserResponseDto> register(@Valid @RequestBody UserCreateRequestDto requestDto){

      UserResponseDto createdUser = userService.register(requestDto);

      return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
   }

   @GetMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto){

         LoginResponseDto loggedInUser = userService.login(loginRequestDto);

         return ResponseEntity.ok(loggedInUser);

   }

   @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable Long id, Authentication authentication){
            UserResponseDto dto = userService.getById(id);
            return ResponseEntity.ok(dto);
   }

}
