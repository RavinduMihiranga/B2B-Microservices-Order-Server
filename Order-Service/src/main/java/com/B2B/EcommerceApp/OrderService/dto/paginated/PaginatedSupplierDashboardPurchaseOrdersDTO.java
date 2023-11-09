package com.B2B.EcommerceApp.OrderService.dto.paginated;

import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedSupplierDashboardPurchaseOrdersDTO {
    private List<SupplierDashboardPurchaseOrdersDTO> list;
    private long dataCount;
}
