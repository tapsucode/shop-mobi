package com.example.accountservice.services;


import com.example.accountservice.dtos.request.AccountRegistrationRequest;
import com.example.accountservice.dtos.response.AccountRegistrationResponse;

public interface AccountService {

    AccountRegistrationResponse registerUser(AccountRegistrationRequest accountRegistrationRequest);

    boolean confirmUser(Long userId,String confirmationCode);
}
