package com.B2B.EcommerceApp.OrderService.dto.response;

import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
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
public class CustomerDashboardOrderDetailsDTO {
    List<OrderDetailsDTO> orderDetailsDTOList;
    private int ID;
    private float totalPrice;
    private Date deliveryDate;
    private String deliveryAddress;
    private OrderStatus orderStatus;
    private int totalProducts;

}
