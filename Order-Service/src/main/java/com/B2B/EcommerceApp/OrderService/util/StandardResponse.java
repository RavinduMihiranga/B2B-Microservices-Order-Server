package com.B2B.EcommerceApp.OrderService.util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class StandardResponse {
    private int code;
    private String message;
    private Object data;
}
