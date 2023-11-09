package com.B2B.EcommerceApp.OrderService.dto;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import com.B2B.EcommerceApp.OrderService.entity.OrderDetails;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDTO {
    private int ID;

    private String customerSyscoID;

    private String customerName;

    private float totalPrice;

    private String deliveryAddress;

    private Date deliveryDate;

    private OrderStatus orderStatus;

    private List<OrderDetails> orderDetailsList;
}
