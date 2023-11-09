package com.B2B.EcommerceApp.OrderService.dto.response;

import com.B2B.EcommerceApp.OrderService.dto.CartDTO;
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
public class CartResponseDTO {
    private String customerSyscoID;

    private String customerName;

    private float totalPrice;

    private String deliveryAddress;

    private Date deliveryDate;

    private OrderStatus orderStatus;

    private List<CartDTO> cartDTOList;

    public CartResponseDTO(float totalPrice, List<CartDTO> cartDTOList) {
        this.totalPrice = totalPrice;
        this.cartDTOList = cartDTOList;
    }
}
