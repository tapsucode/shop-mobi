package com.example.productservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Category {

    @Id
    private Long id;

    private String categoryName;

    private LocalDateTime createDate;

    private Long accountId;

    private List<Product> products;
}
