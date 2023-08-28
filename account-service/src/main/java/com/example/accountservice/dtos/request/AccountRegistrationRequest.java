package com.example.accountservice.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationRequest {

    @NotBlank
    @Size(min = 8,max = 32)
    private String username;

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")
    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String fullName;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

}
