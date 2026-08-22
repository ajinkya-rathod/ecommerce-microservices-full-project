package com.userservice.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {

    @NotBlank(message = "Name Is Required")
    private String name;

    @Email(message = "valid email required")
    @NotBlank(message = "Email Is Required")
    private String email;

    @NotBlank(message = "Password Required")
    @Size(min = 6,message = "password must be atlist 6 charactor")
    private String password;

    private String phone;

}
