package com.B2B.EcommerceApp.OrderService.dto.request;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderDetailsUpdateDTO {
    private OrderStatus orderStatus;

    private String deliveryAddress;

    private Date deliveryDate;
}
