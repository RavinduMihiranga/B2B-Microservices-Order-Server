package com.B2B.EcommerceApp.OrderService.dto.request;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class OrderStatusUpdateDTO {
    private boolean supplyStatus;
}
