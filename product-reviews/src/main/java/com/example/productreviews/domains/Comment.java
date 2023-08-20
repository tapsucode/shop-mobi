package com.example.productreviews.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Comment {

    @Id
    private Long id;

    private Long productId;

    private Long accountId;

    private String body;
}
