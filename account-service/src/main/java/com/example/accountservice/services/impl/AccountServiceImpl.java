package com.example.accountservice.services.impl;


import com.example.accountservice.domains.Account;
import com.example.accountservice.domains.ConfirmationCode;
import com.example.accountservice.domains.Role;
import com.example.accountservice.dtos.request.AccountRegistrationRequest;
import com.example.accountservice.dtos.response.AccountRegistrationResponse;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.repositories.RoleRepository;
import com.example.accountservice.repositories.UserConfirmationRepository;
import com.example.accountservice.services.AccountService;
import com.example.commonservice.exceptions.ResourceConflictException;
import com.example.commonservice.exceptions.ResourceNotFoundException;
import com.example.commonservice.kafkabridge.AccountVerification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final StreamBridge streamBridge;
    private final RoleRepository roleRepository;
    private final AccountRepository accountRepository;
    private final UserConfirmationRepository userConfirmationRepository;
    public String ACCOUNT_CONFIRM_TOPIC = "accountConfirm-out-0";


    @Override
    public AccountRegistrationResponse registerUser(AccountRegistrationRequest accountRegistrationRequest) {

        // Check if the email and username exist in the database
        if(accountRepository.findByUsername(accountRegistrationRequest.getUsername()).isPresent()){
            throw new ResourceConflictException("User with the provided username already exists.");
        }

        if(accountRepository.findByEmail(accountRegistrationRequest.getEmail()).isPresent()){
            throw new ResourceConflictException("User with the provided email already exists.");
        }
        // Create confirmation code,set account information and save account in database
        String code = UUID.randomUUID().toString();
        Account account = new Account();
        BeanUtils.copyProperties(accountRegistrationRequest, account);
        account.setStatus(Account.Status.PENDING);
        Role role = roleRepository.findByName(Role.Name.CUSTOMER).orElseThrow();
        List<Role> listRole = new ArrayList<>();
        listRole.add(role);
        account.setRoles(listRole);
        account.setPassword(accountRegistrationRequest.getPassword());

        AccountRegistrationResponse accountRegistrationResponse = new AccountRegistrationResponse();
        ConfirmationCode userConfirmation = new ConfirmationCode(null, code, ConfirmationCode.Status.USED, account);

        BeanUtils.copyProperties(accountRepository.save(account), accountRegistrationResponse);
        userConfirmationRepository.save(userConfirmation);

        String confirmURL =String.format("/api/v1/auth/confirm/%s/%s", accountRegistrationResponse.getId(),code);

        AccountVerification accountVerification = new AccountVerification(accountRegistrationResponse.getEmail(),confirmURL);

        // Send email information and confirmURL to notification-service
        streamBridge.send(ACCOUNT_CONFIRM_TOPIC,accountVerification);


        return accountRegistrationResponse;
    }

    @Override
    public boolean confirmUser(Long accountId, String code) {
        // Check if the account id and confirmation code exist in the database
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        ConfirmationCode confirmationCode = userConfirmationRepository
                .findByAccountIdAndCodeAndStatus(accountId, code, null)
                .orElseThrow(() -> new ResourceNotFoundException("Confirmation code not found"));

        confirmationCode.setStatus(ConfirmationCode.Status.EXPIRED);
        userConfirmationRepository.save(confirmationCode);
        account.setStatus(Account.Status.ACTIVATED);
        accountRepository.save(account);

        return true;
    }

}
