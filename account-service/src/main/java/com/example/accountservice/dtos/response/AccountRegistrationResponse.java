package com.example.accountservice.dtos.response;

import com.example.accountservice.domains.Account;
import com.example.accountservice.domains.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegistrationResponse {
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String address;

    private String phoneNumber;

    private Account.Status status;

}
