package com.B2B.EcommerceApp.OrderService.dto.queryInterfaces;

import java.util.Date;

public interface SupplierDashboardPurchaseOrdersInterface {

    String getCustomerSyscoID();

    String getCustomerName();

    Date getDeliveryDate();

    String getDeliveryAddress();

    int getOrderDetailsID();

    String getProductSyscoID();

    String getProductName();

    float getQuantity();
}
