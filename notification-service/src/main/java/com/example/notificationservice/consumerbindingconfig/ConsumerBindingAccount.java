package com.example.notificationservice.consumerbindingconfig;

import com.example.commonservice.kafkabridge.AccountVerification;
import com.example.notificationservice.service.AccountRegistration;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class ConsumerBindingAccount {

    private final AccountRegistration accountRegistration;

    //Listen to the event from the account-confirmation-notification topic.
    //Get the email information and confirmURL from the account-service side, and then send the account verification email.
    @Bean
    public Consumer<AccountVerification> accountConfirmListener(){
        return msg ->
            accountRegistration.confirmAccount(msg.getEmail(),msg.getConfirmURL());
    }
}
