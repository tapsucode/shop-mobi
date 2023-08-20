package com.example.orderservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class OrderItem {

    @Id
    private Long id;

    private Long productId;

    private int quantity;

    private double subtotal;

    private Long orderId;

}
