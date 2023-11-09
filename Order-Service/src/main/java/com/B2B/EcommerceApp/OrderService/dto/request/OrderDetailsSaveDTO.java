package com.B2B.EcommerceApp.OrderService.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDetailsSaveDTO {
    private String productSyscoID;

    private String productName;

    private float quantity;

    private float price;

    private String supplierSyscoID;

    private String supplierName;
}
