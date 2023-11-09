package com.B2B.EcommerceApp.OrderService.util.mappers;


import com.B2B.EcommerceApp.OrderService.dto.CartDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.queryInterfaces.SupplierDashboardPurchaseOrdersInterface;
import com.B2B.EcommerceApp.OrderService.dto.request.CartSaveDTO;
import com.B2B.EcommerceApp.OrderService.dto.request.OrderSaveDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.entity.Cart;
import com.B2B.EcommerceApp.OrderService.entity.Order;
import com.B2B.EcommerceApp.OrderService.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order OrderSaveDTO_to_OrderEntity(OrderSaveDTO orderSaveDTO);

    OrderDTO OrderEntity_to_OrderDTO(Order order);

    List<OrderDTO> OrderEntityList_to_OrderDTOList(List<Order> orderList);


    List<SupplierDashboardPurchaseOrdersDTO> purchaseOrdersEntityList_to_SupplierDashboardList(List<SupplierDashboardPurchaseOrdersInterface> purchaseOrdersDTO);

    SupplierDashboardPurchaseOrdersDTO supplierDashboardInterface_to_DTO(SupplierDashboardPurchaseOrdersInterface purchaseOrder);


    List<CustomerDashboardOrdersDTO> OrderPageList_to_CustomerDashboardOrderDTOsList(Page<Order> orders);

    CustomerDashboardOrderDetailsDTO OrderDetails_to_CustomerDashboardOrderDetailsDTO(Order order);

    List<OrderDetailsDTO> OrderDetailsEntity_to_OrderDetailsDTOList(List<OrderDetails> orderDetails);

    OrderDetailsDTO OrderDetailsEntity_to_OrderDetailsDTO(OrderDetails orderDetails);

    Cart CartSaveDTO_to_CartEntity(CartSaveDTO cartSaveDTO);

    List<CartDTO> CartEntityList_to_CartDTOList(List<Cart> carts);
}
