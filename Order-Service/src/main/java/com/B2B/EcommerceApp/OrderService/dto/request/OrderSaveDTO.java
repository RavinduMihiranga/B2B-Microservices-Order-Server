package com.B2B.EcommerceApp.OrderService.dto.request;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderSaveDTO {
    private String customerSyscoID;

    private String customerName;

    private float totalPrice;

    private String deliveryAddress;

    private Date deliveryDate;

    private OrderStatus orderStatus;

    private List<OrderDetailsSaveDTO> orderDetailsSet;
}
