package com.B2B.EcommerceApp.OrderService.service;

import com.B2B.EcommerceApp.OrderService.dto.OrderDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedCustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedSupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.request.*;
import com.B2B.EcommerceApp.OrderService.dto.response.CartResponseDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;

import java.util.List;

public interface OrderService {
    OrderSaveDTO saveOrder(OrderSaveDTO orderSaveDTO, String sessionID);

    PaginatedCustomerDashboardOrdersDTO getAllOrdersByCustomerAndOrderStatus(String customerSyscoID, String orderStatus, int page, int size);

    PaginatedSupplierDashboardPurchaseOrdersDTO getAllOrdersBySupplierAndOrderStatus(String supplierSyscoID, boolean supplyStatus, int page, int size);

    SupplierDashboardPurchaseOrdersDTO getPurchaseOrderById(int orderDetailsID);

    CustomerDashboardOrderDetailsDTO getCustomerOrderById(int id);

    OrderDTO updateOrderByCustomer(int id, OrderDetailsUpdateDTO orderDetailsUpdateDTO);

    OrderDetailsDTO updateSupplyStatusByID(int id, OrderStatusUpdateDTO orderStatusUpdateDTO);

    CartResponseDTO saveCart(CartSaveDTO cartSaveDTO, String sessionID);

    CartResponseDTO getCartDetails(String sessionID);


    CartResponseDTO updateCart(String sessionID, CartUpdateDTO cartUpdateDTO);

    CartResponseDTO deleteCart(String sessionID, int cartID);

//    OrderDTO getOrderById(int ID);
//
//    OrderDTO updateOrderDetailsByID(int id, OrderDetailsUpdateDTO orderDetailsUpdateDTO);
//
//    OrderDTO updateOrderStatusByID(int id, String orderStatus);
//
//    List<OrderDTO> getAllOrdersByCustomerSyscoID(String customerSyscoID, String orderStatus);
//
//    List<OrderDTO> getAllOrdersBySupplierSyscoID(String supplierSyscoID, String orderStatus);
}
