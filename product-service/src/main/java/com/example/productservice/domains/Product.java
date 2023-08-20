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
public class Product {

    @Id
    private Long id;

    private String description;

    private double price;

    private double discount;

    private int quantity;

    private String image;

    private String body;

    private String origin;

    private byte warrantyDuration;

    private LocalDateTime createdDate;

    private Long accountId;

    private List<Category> categories;

}
