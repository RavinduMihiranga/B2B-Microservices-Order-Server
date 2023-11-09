package com.B2B.EcommerceApp.OrderService.dto.paginated;

import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedCustomerDashboardOrdersDTO {
    private List<CustomerDashboardOrdersDTO> list;
    private long dataCount;
}
