package com.example.accountservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class ConfirmationCode {

    @Id
    private Long id;

    private String code;

    private Status status;

    private Long accountId;
    public enum Status {
        EXPIRED,
        USED
    }
}
