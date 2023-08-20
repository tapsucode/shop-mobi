package com.example.saleservice.domains;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table
public class Warehouse {

    @Id
    private Long id;

    private Long accountId;

    private List<CancelOrder> cancelOrders;

    private List<DetailOrder> detailOrders;

    private List<Inventory> inventories;
}
