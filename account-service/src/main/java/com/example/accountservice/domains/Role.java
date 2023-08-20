package com.example.accountservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "role")
public class Role {
    @Id
    private Long id;

    private Name name;

    private Long accountId;

    private List<Account> accounts;

    public enum Name {
        ADMIN,
        CUSTOMER,
        SALE
    }

}

