package com.B2B.EcommerceApp.OrderService.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CartDTO {
    private int cartID;

    private String sessionID;

    private String productSyscoID;

    private String productName;

    private float quantity;

    private float price;
}
