package com.B2B.EcommerceApp.OrderService.dto.response;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class CustomerDashboardOrdersDTO {
    private int ID;
    private float totalPrice;
    private Date deliveryDate;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private int totalProducts;
}
