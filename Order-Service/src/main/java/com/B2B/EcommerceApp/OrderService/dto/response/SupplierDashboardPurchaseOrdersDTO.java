package com.B2B.EcommerceApp.OrderService.dto.response;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class SupplierDashboardPurchaseOrdersDTO {
    //    Order
    private String customerSyscoID;
    private String customerName;
    private Date deliveryDate;
    private String deliveryAddress;

    //    Order Details
    private int orderDetailsID;
    private String productSyscoID;
    private String productName;
    private float quantity;

}
