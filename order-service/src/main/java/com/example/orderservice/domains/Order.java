package com.example.orderservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Order {

    @Id
    private Long id;

    private Long accountId;

    private double totalPay;

    private LocalDateTime orderDate;

    private String shippingAddress;

    private Payment payment;

    private Status status;

    private String note;

    private List<OrderItem> orderItems;

    public enum Payment{
        CASH, // tien mat
        CARD, // the
        COD
    }
    public enum Status{
        PROCESSING, // dang xu li
        SHIPPED, // da gui
        COMPLETED // hoan thanh
    }
}
