package com.B2B.EcommerceApp.OrderService.util.mappers;

import com.B2B.EcommerceApp.OrderService.dto.CartDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.queryInterfaces.SupplierDashboardPurchaseOrdersInterface;
import com.B2B.EcommerceApp.OrderService.dto.request.CartSaveDTO;
import com.B2B.EcommerceApp.OrderService.dto.request.OrderDetailsSaveDTO;
import com.B2B.EcommerceApp.OrderService.dto.request.OrderSaveDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.entity.Cart;
import com.B2B.EcommerceApp.OrderService.entity.Order;
import com.B2B.EcommerceApp.OrderService.entity.OrderDetails;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-09T10:48:16+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order OrderSaveDTO_to_OrderEntity(OrderSaveDTO orderSaveDTO) {
        if ( orderSaveDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setCustomerSyscoID( orderSaveDTO.getCustomerSyscoID() );
        order.setCustomerName( orderSaveDTO.getCustomerName() );
        order.setTotalPrice( orderSaveDTO.getTotalPrice() );
        order.setDeliveryAddress( orderSaveDTO.getDeliveryAddress() );
        order.setDeliveryDate( orderSaveDTO.getDeliveryDate() );
        order.setOrderStatus( orderSaveDTO.getOrderStatus() );
        order.setOrderDetailsSet( orderDetailsSaveDTOListToOrderDetailsSet( orderSaveDTO.getOrderDetailsSet() ) );

        return order;
    }

    @Override
    public OrderDTO OrderEntity_to_OrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setID( order.getID() );
        orderDTO.setCustomerSyscoID( order.getCustomerSyscoID() );
        orderDTO.setCustomerName( order.getCustomerName() );
        orderDTO.setTotalPrice( order.getTotalPrice() );
        orderDTO.setDeliveryAddress( order.getDeliveryAddress() );
        orderDTO.setDeliveryDate( order.getDeliveryDate() );
        orderDTO.setOrderStatus( order.getOrderStatus() );

        return orderDTO;
    }

    @Override
    public List<OrderDTO> OrderEntityList_to_OrderDTOList(List<Order> orderList) {
        if ( orderList == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( orderList.size() );
        for ( Order order : orderList ) {
            list.add( OrderEntity_to_OrderDTO( order ) );
        }

        return list;
    }

    @Override
    public List<SupplierDashboardPurchaseOrdersDTO> purchaseOrdersEntityList_to_SupplierDashboardList(List<SupplierDashboardPurchaseOrdersInterface> purchaseOrdersDTO) {
        if ( purchaseOrdersDTO == null ) {
            return null;
        }

        List<SupplierDashboardPurchaseOrdersDTO> list = new ArrayList<SupplierDashboardPurchaseOrdersDTO>( purchaseOrdersDTO.size() );
        for ( SupplierDashboardPurchaseOrdersInterface supplierDashboardPurchaseOrdersInterface : purchaseOrdersDTO ) {
            list.add( supplierDashboardInterface_to_DTO( supplierDashboardPurchaseOrdersInterface ) );
        }

        return list;
    }

    @Override
    public SupplierDashboardPurchaseOrdersDTO supplierDashboardInterface_to_DTO(SupplierDashboardPurchaseOrdersInterface purchaseOrder) {
        if ( purchaseOrder == null ) {
            return null;
        }

        SupplierDashboardPurchaseOrdersDTO supplierDashboardPurchaseOrdersDTO = new SupplierDashboardPurchaseOrdersDTO();

        supplierDashboardPurchaseOrdersDTO.setCustomerSyscoID( purchaseOrder.getCustomerSyscoID() );
        supplierDashboardPurchaseOrdersDTO.setCustomerName( purchaseOrder.getCustomerName() );
        supplierDashboardPurchaseOrdersDTO.setDeliveryDate( purchaseOrder.getDeliveryDate() );
        supplierDashboardPurchaseOrdersDTO.setDeliveryAddress( purchaseOrder.getDeliveryAddress() );
        supplierDashboardPurchaseOrdersDTO.setOrderDetailsID( purchaseOrder.getOrderDetailsID() );
        supplierDashboardPurchaseOrdersDTO.setProductSyscoID( purchaseOrder.getProductSyscoID() );
        supplierDashboardPurchaseOrdersDTO.setProductName( purchaseOrder.getProductName() );
        supplierDashboardPurchaseOrdersDTO.setQuantity( purchaseOrder.getQuantity() );

        return supplierDashboardPurchaseOrdersDTO;
    }

    @Override
    public List<CustomerDashboardOrdersDTO> OrderPageList_to_CustomerDashboardOrderDTOsList(Page<Order> orders) {
        if ( orders == null ) {
            return null;
        }

        List<CustomerDashboardOrdersDTO> list = new ArrayList<CustomerDashboardOrdersDTO>();
        for ( Order order : orders ) {
            list.add( orderToCustomerDashboardOrdersDTO( order ) );
        }

        return list;
    }

    @Override
    public CustomerDashboardOrderDetailsDTO OrderDetails_to_CustomerDashboardOrderDetailsDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        CustomerDashboardOrderDetailsDTO customerDashboardOrderDetailsDTO = new CustomerDashboardOrderDetailsDTO();

        customerDashboardOrderDetailsDTO.setID( order.getID() );
        customerDashboardOrderDetailsDTO.setTotalPrice( order.getTotalPrice() );
        customerDashboardOrderDetailsDTO.setDeliveryDate( order.getDeliveryDate() );
        customerDashboardOrderDetailsDTO.setDeliveryAddress( order.getDeliveryAddress() );
        customerDashboardOrderDetailsDTO.setOrderStatus( order.getOrderStatus() );

        return customerDashboardOrderDetailsDTO;
    }

    @Override
    public List<OrderDetailsDTO> OrderDetailsEntity_to_OrderDetailsDTOList(List<OrderDetails> orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        List<OrderDetailsDTO> list = new ArrayList<OrderDetailsDTO>( orderDetails.size() );
        for ( OrderDetails orderDetails1 : orderDetails ) {
            list.add( OrderDetailsEntity_to_OrderDetailsDTO( orderDetails1 ) );
        }

        return list;
    }

    @Override
    public OrderDetailsDTO OrderDetailsEntity_to_OrderDetailsDTO(OrderDetails orderDetails) {
        if ( orderDetails == null ) {
            return null;
        }

        OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

        orderDetailsDTO.setID( orderDetails.getID() );
        orderDetailsDTO.setProductSyscoID( orderDetails.getProductSyscoID() );
        orderDetailsDTO.setProductName( orderDetails.getProductName() );
        orderDetailsDTO.setQuantity( orderDetails.getQuantity() );
        orderDetailsDTO.setPrice( orderDetails.getPrice() );
        orderDetailsDTO.setSupplierSyscoID( orderDetails.getSupplierSyscoID() );
        orderDetailsDTO.setSupplierName( orderDetails.getSupplierName() );
        orderDetailsDTO.setSupplyStatus( orderDetails.isSupplyStatus() );

        return orderDetailsDTO;
    }

    @Override
    public Cart CartSaveDTO_to_CartEntity(CartSaveDTO cartSaveDTO) {
        if ( cartSaveDTO == null ) {
            return null;
        }

        Cart cart = new Cart();

        cart.setSessionID( cartSaveDTO.getSessionID() );
        cart.setProductSyscoID( cartSaveDTO.getProductSyscoID() );
        cart.setProductName( cartSaveDTO.getProductName() );
        cart.setQuantity( cartSaveDTO.getQuantity() );
        cart.setPrice( cartSaveDTO.getPrice() );

        return cart;
    }

    @Override
    public List<CartDTO> CartEntityList_to_CartDTOList(List<Cart> carts) {
        if ( carts == null ) {
            return null;
        }

        List<CartDTO> list = new ArrayList<CartDTO>( carts.size() );
        for ( Cart cart : carts ) {
            list.add( cartToCartDTO( cart ) );
        }

        return list;
    }

    protected OrderDetails orderDetailsSaveDTOToOrderDetails(OrderDetailsSaveDTO orderDetailsSaveDTO) {
        if ( orderDetailsSaveDTO == null ) {
            return null;
        }

        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setProductSyscoID( orderDetailsSaveDTO.getProductSyscoID() );
        orderDetails.setProductName( orderDetailsSaveDTO.getProductName() );
        orderDetails.setQuantity( orderDetailsSaveDTO.getQuantity() );
        orderDetails.setPrice( orderDetailsSaveDTO.getPrice() );
        orderDetails.setSupplierSyscoID( orderDetailsSaveDTO.getSupplierSyscoID() );
        orderDetails.setSupplierName( orderDetailsSaveDTO.getSupplierName() );

        return orderDetails;
    }

    protected Set<OrderDetails> orderDetailsSaveDTOListToOrderDetailsSet(List<OrderDetailsSaveDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<OrderDetails> set = new HashSet<OrderDetails>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( OrderDetailsSaveDTO orderDetailsSaveDTO : list ) {
            set.add( orderDetailsSaveDTOToOrderDetails( orderDetailsSaveDTO ) );
        }

        return set;
    }

    protected CustomerDashboardOrdersDTO orderToCustomerDashboardOrdersDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        CustomerDashboardOrdersDTO customerDashboardOrdersDTO = new CustomerDashboardOrdersDTO();

        customerDashboardOrdersDTO.setID( order.getID() );
        customerDashboardOrdersDTO.setTotalPrice( order.getTotalPrice() );
        customerDashboardOrdersDTO.setDeliveryDate( order.getDeliveryDate() );
        customerDashboardOrdersDTO.setDeliveryAddress( order.getDeliveryAddress() );
        customerDashboardOrdersDTO.setOrderStatus( order.getOrderStatus() );

        return customerDashboardOrdersDTO;
    }

    protected CartDTO cartToCartDTO(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartDTO cartDTO = new CartDTO();

        cartDTO.setCartID( cart.getCartID() );
        cartDTO.setSessionID( cart.getSessionID() );
        cartDTO.setProductSyscoID( cart.getProductSyscoID() );
        cartDTO.setProductName( cart.getProductName() );
        cartDTO.setQuantity( cart.getQuantity() );
        cartDTO.setPrice( cart.getPrice() );

        return cartDTO;
    }
}
