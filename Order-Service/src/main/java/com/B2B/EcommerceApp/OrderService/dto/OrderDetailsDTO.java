package com.B2B.EcommerceApp.OrderService.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDetailsDTO {
    private int ID;

    private String productSyscoID;

    private String productName;

    private float quantity;

    private float price;

    private String supplierSyscoID;

    private String supplierName;

    private boolean supplyStatus;

    public boolean isSupplyStatus() {
        return supplyStatus;
    }
}
