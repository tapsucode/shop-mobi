package com.example.saleservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class DetailOrder {

    @Id
    private Long id;

    private Long productId;

    private Long orderId;

    private LocalDateTime orderDate;

    private int quantity;

    private Double totalAmount;

    private Warehouse warehouse;
}
