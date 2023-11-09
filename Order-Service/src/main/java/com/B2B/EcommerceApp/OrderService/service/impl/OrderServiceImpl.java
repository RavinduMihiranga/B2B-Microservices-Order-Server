package com.B2B.EcommerceApp.OrderService.service.impl;

import com.B2B.EcommerceApp.OrderService.dto.CartDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedCustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedSupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.queryInterfaces.SupplierDashboardPurchaseOrdersInterface;
import com.B2B.EcommerceApp.OrderService.dto.request.*;
import com.B2B.EcommerceApp.OrderService.dto.response.CartResponseDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.entity.Cart;
import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import com.B2B.EcommerceApp.OrderService.entity.Order;
import com.B2B.EcommerceApp.OrderService.entity.OrderDetails;
import com.B2B.EcommerceApp.OrderService.exception.DuplicateKeyException;
import com.B2B.EcommerceApp.OrderService.exception.NotFoundException;
import com.B2B.EcommerceApp.OrderService.repo.CartRepo;
import com.B2B.EcommerceApp.OrderService.repo.OrderDetailsRepo;
import com.B2B.EcommerceApp.OrderService.repo.OrderRepo;
import com.B2B.EcommerceApp.OrderService.service.OrderService;
import com.B2B.EcommerceApp.OrderService.util.mappers.OrderMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private ModelMapper modelMapper;

    private static List<OrderDetails> getOrderDetailsList(OrderSaveDTO orderSaveDTO, Order order) {
        List<OrderDetails> orderDetails = new ArrayList<>();
        int i = 0;
        for (OrderDetailsSaveDTO orderDetailsSaveDTO : orderSaveDTO.getOrderDetailsSet()) {
            OrderDetails orderDetails1 = new OrderDetails(
                    orderDetailsSaveDTO.getProductSyscoID(),
                    orderDetailsSaveDTO.getProductName(),
                    orderDetailsSaveDTO.getQuantity(),
                    orderDetailsSaveDTO.getPrice(),
                    orderDetailsSaveDTO.getSupplierSyscoID(),
                    orderDetailsSaveDTO.getSupplierName(),
                    order
            );
            orderDetails.add(orderDetails1);

        }
        return orderDetails;
    }

    @Override
    @Transactional
    public OrderSaveDTO saveOrder(OrderSaveDTO orderSaveDTO, String sessionID) {
        float totPrice = 0;
        if(cartRepo.existsBySessionID(sessionID)){
            List<Cart> cartList = cartRepo.findAllBySessionIDEquals(sessionID);
            for (Cart cart: cartList) {
                totPrice += cart.getPrice() * cart.getQuantity();
                cartRepo.delete(cart);
            }
        }
        Order order = new Order(
                orderSaveDTO.getCustomerSyscoID(),
                orderSaveDTO.getCustomerName(),
                totPrice,
                orderSaveDTO.getDeliveryAddress(),
                orderSaveDTO.getDeliveryDate(),
                orderSaveDTO.getOrderStatus()
        );
        if (!orderRepo.existsById(order.getID())) {
            orderRepo.save(order);
            List<OrderDetails> orderDetails = getOrderDetailsList(orderSaveDTO, order);
            if (!orderDetails.isEmpty()) {
                orderDetailsRepo.saveAll(orderDetails);
            }
            return orderSaveDTO;
        } else {
            throw new DuplicateKeyException("Existing Order");
        }
    }

    @Override
    public PaginatedCustomerDashboardOrdersDTO getAllOrdersByCustomerAndOrderStatus(String customerSyscoID, String orderStatus, int page, int size) {
        OrderStatus orderStatus1 = OrderStatus.valueOf(orderStatus);
        Page<Order> orders = orderRepo.findAllByCustomerSyscoIDEqualsAndOrderStatusEqualsOrderByIDDesc(customerSyscoID, orderStatus1, PageRequest.of(page, size));
        if (!orders.isEmpty()) {
            List<CustomerDashboardOrdersDTO> list = orderMapper.OrderPageList_to_CustomerDashboardOrderDTOsList(orders);
            List<Order> orders1 = orders.getContent();
            for (int i = 0; i < orders1.size(); i++) {
                int totalProducts = orderDetailsRepo.countAllByOrders(orders1.get(i));
                list.get(i).setTotalProducts(totalProducts);
            }

            return new PaginatedCustomerDashboardOrdersDTO(
                    list,
                    orderRepo.countAllByCustomerSyscoIDEqualsAndOrderStatusEquals(customerSyscoID, orderStatus1)
            );

        } else {
                throw new NotFoundException("Orders Not Found");
        }
    }

    @Override
    public PaginatedSupplierDashboardPurchaseOrdersDTO getAllOrdersBySupplierAndOrderStatus(String supplierSyscoID, boolean supplyStatus, int page, int size) {
        List<SupplierDashboardPurchaseOrdersInterface> purchaseOrdersDTO = orderDetailsRepo.getAllPurchaseOrders(supplierSyscoID, supplyStatus, PageRequest.of(page, size));
        if (!purchaseOrdersDTO.isEmpty()) {
            List<SupplierDashboardPurchaseOrdersDTO> list = orderMapper.purchaseOrdersEntityList_to_SupplierDashboardList(purchaseOrdersDTO);
            return new PaginatedSupplierDashboardPurchaseOrdersDTO(
                    list,
                    orderDetailsRepo.countAllPurchaseOrders(supplierSyscoID, supplyStatus)
            );
        } else {
            throw new NotFoundException("Purchase orders not found");
        }
    }

    @Override
    public SupplierDashboardPurchaseOrdersDTO getPurchaseOrderById(int orderDetailsID) {
        if (orderDetailsRepo.existsById(orderDetailsID)) {
            SupplierDashboardPurchaseOrdersInterface purchaseOrder = orderDetailsRepo.getPurchaseOrderById(orderDetailsID);
            return orderMapper.supplierDashboardInterface_to_DTO(purchaseOrder);
        } else {
            throw new NotFoundException("Purchase order not found");
        }
    }

    @Override
    public CustomerDashboardOrderDetailsDTO getCustomerOrderById(int id) {
        if (orderRepo.existsById(id)) {
            Order order = orderRepo.getReferenceById(id);
            int totalProducts = orderDetailsRepo.countAllByOrders(order);
            CustomerDashboardOrderDetailsDTO orderDetailsDTO = orderMapper.OrderDetails_to_CustomerDashboardOrderDetailsDTO(order);
            orderDetailsDTO.setTotalProducts(totalProducts);
            List<OrderDetails> orderDetails = orderDetailsRepo.findAllByOrdersOrderByID(order);
            List<OrderDetailsDTO> orderDetailsDTOList = orderMapper.OrderDetailsEntity_to_OrderDetailsDTOList(orderDetails);
            orderDetailsDTO.setOrderDetailsDTOList(orderDetailsDTOList);
            return orderDetailsDTO;
        } else {
            throw new NotFoundException("Purchase order not found");
        }
    }

    @Override
    @Transactional
    public OrderDTO updateOrderByCustomer(int id, OrderDetailsUpdateDTO orderDetailsUpdateDTO) {
        if(orderRepo.existsById(id)){
            Order order = orderRepo.getReferenceById(id);
            order.setDeliveryDate(orderDetailsUpdateDTO.getDeliveryDate());
            order.setDeliveryAddress(orderDetailsUpdateDTO.getDeliveryAddress());
            order.setOrderStatus(orderDetailsUpdateDTO.getOrderStatus());
            orderRepo.save(order);
            if(orderDetailsUpdateDTO.getOrderStatus() == OrderStatus.valueOf("cancelled")){
                List<OrderDetails> orderDetailsList = orderDetailsRepo.findAllByOrdersOrderByID(order);
                for (OrderDetails orderDetails : orderDetailsList) {
                    orderDetails.setSupplyStatus(true);
                }
                orderDetailsRepo.saveAll(orderDetailsList);
            }
            return orderMapper.OrderEntity_to_OrderDTO(order);
        } else {
            throw new NotFoundException("Order not found");
        }
    }

    @Override
    public OrderDetailsDTO updateSupplyStatusByID(int id, OrderStatusUpdateDTO orderStatusUpdateDTO) {
        if (orderDetailsRepo.existsById(id)) {
            OrderDetails orderDetails = orderDetailsRepo.getReferenceById(id);
            orderDetails.setSupplyStatus(orderStatusUpdateDTO.isSupplyStatus());
            orderDetailsRepo.save(orderDetails);
            Order order = orderDetails.getOrders();
            List<OrderDetails> orderDetailsList = orderDetailsRepo.findAllByOrdersOrderByID(order);
            int size = orderDetailsList.size();
            int supplies = 0;
            int notSupplies = 0;
            for (OrderDetails orderDetails1 : orderDetailsList) {
                boolean supplyStatus = orderDetails1.isSupplyStatus();
                if (!supplyStatus){
                    notSupplies++;
                } else{
                    supplies++;
                }
            }
            if (size == supplies){
                order.setOrderStatus(OrderStatus.valueOf("completed"));
            } else if(size == notSupplies){
                order.setOrderStatus(OrderStatus.valueOf("placed"));
            } else {
                order.setOrderStatus(OrderStatus.valueOf("partial_supply"));
            }
            orderRepo.save(order);
            return orderMapper.OrderDetailsEntity_to_OrderDetailsDTO(orderDetails);
        } else {
            throw new NotFoundException("Purchase order not found");
        }
    }

    @Override
    public CartResponseDTO saveCart(CartSaveDTO cartSaveDTO, String sessionID) {
        if(!cartRepo.existsBySessionIDEqualsAndProductSyscoIDEquals(sessionID, cartSaveDTO.getProductSyscoID())){
            Cart cart = orderMapper.CartSaveDTO_to_CartEntity(cartSaveDTO);
            cartRepo.save(cart);
            List<Cart> carts = cartRepo.findAllBySessionIDEquals(sessionID);
            List<CartDTO> cartDTOList = orderMapper.CartEntityList_to_CartDTOList(carts);
            float totPrice = 0;
            for (CartDTO cartDTO: cartDTOList) {
                totPrice += cartDTO.getPrice() * cartDTO.getQuantity();
            }
            return new CartResponseDTO(totPrice, cartDTOList);
        } else {
            throw new DuplicateKeyException("Cart already exists");
        }

    }

    @Override
    public CartResponseDTO getCartDetails(String sessionID) {
        List<Cart> carts = cartRepo.findAllBySessionIDEquals(sessionID);
        if(!carts.isEmpty()){
            List<CartDTO> cartDTOList = orderMapper.CartEntityList_to_CartDTOList(carts);
            float totPrice = 0;
            for (CartDTO cartDTO: cartDTOList) {
                totPrice += cartDTO.getPrice() * cartDTO.getQuantity();
            }
            return new CartResponseDTO(totPrice, cartDTOList);
        } else {
            throw new NotFoundException("Cart details not found");
        }
    }

    @Override
    public CartResponseDTO updateCart(String sessionID, CartUpdateDTO cartUpdateDTO) {
        if(cartRepo.existsBySessionIDEqualsAndProductSyscoIDEquals(sessionID,cartUpdateDTO.getProductSyscoID())){
            Cart cart = cartRepo.findAllBySessionIDEqualsAndProductSyscoIDEquals(sessionID, cartUpdateDTO.getProductSyscoID());
            cart.setPrice(cartUpdateDTO.getPrice());
            cart.setQuantity(cartUpdateDTO.getQuantity());
            cart.setProductName(cartUpdateDTO.getProductName());
            cart.setProductSyscoID(cartUpdateDTO.getProductSyscoID());
            cartRepo.save(cart);
            List<Cart> cartList = cartRepo.findAllBySessionIDEquals(sessionID);
            List<CartDTO> cartDTOList = orderMapper.CartEntityList_to_CartDTOList(cartList);
            float totPrice = 0;
            for (CartDTO cartDTO: cartDTOList) {
                totPrice += cartDTO.getPrice() * cartDTO.getQuantity();
            }
            return new CartResponseDTO(totPrice, cartDTOList);

        }else {
            throw new NotFoundException("Cart details not found");
        }
    }

    @Override
    public CartResponseDTO deleteCart(String sessionID, int cartID) {
        if(cartRepo.existsBySessionIDEqualsAndCartIDEquals(sessionID, cartID)){
            Cart cart = cartRepo.findBySessionIDEqualsAndCartIDEquals(sessionID, cartID);
            cartRepo.delete(cart);
            List<Cart> cartList = cartRepo.findAllBySessionIDEquals(sessionID);
            List<CartDTO> cartDTOList = orderMapper.CartEntityList_to_CartDTOList(cartList);
            float totPrice = 0;
            for (CartDTO cartDTO: cartDTOList) {
                totPrice += cartDTO.getPrice() * cartDTO.getQuantity();
            }
            return new CartResponseDTO(totPrice, cartDTOList);

        }else {
            throw new NotFoundException("Cart details not found");
        }
    }

}
