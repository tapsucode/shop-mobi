package com.example.accountservice.controller;


import com.example.accountservice.dtos.request.AccountRegistrationRequest;
import com.example.accountservice.dtos.response.AccountRegistrationResponse;
import com.example.accountservice.services.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AccountService accountService;
    @PostMapping("signup")
    public AccountRegistrationResponse registerUser(@Valid @RequestBody AccountRegistrationRequest accountRegistrationRequest){
        return accountService.registerUser(accountRegistrationRequest);
    }

    @PostMapping("/confirm/{userId}/{code}")
    public void confirmAccount(@PathVariable Long userId, @PathVariable("code") String confirmationCode){
        accountService.confirmUser(userId,confirmationCode);
    }



}
