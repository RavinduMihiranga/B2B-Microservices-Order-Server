package com.B2B.EcommerceApp.OrderService.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CartUpdateDTO {
    private String productSyscoID;

    private String productName;

    private float quantity;

    private float price;
}
