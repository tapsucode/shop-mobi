package com.example.accountservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "account")
public class Account {
    @Id
    private Long id;

    private String username;

    private String password;

    private String email;

    private String fullName;

    private String address;

    private String phoneNumber;

    private Status status;
    public enum Status {
        PENDING,
        ACTIVATED,
        DEACTIVATED
    }
}
