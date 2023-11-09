package com.B2B.EcommerceApp.OrderService.controller;

import com.B2B.EcommerceApp.OrderService.dto.OrderDTO;
import com.B2B.EcommerceApp.OrderService.dto.OrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedCustomerDashboardOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.paginated.PaginatedSupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.dto.request.*;
import com.B2B.EcommerceApp.OrderService.dto.response.CartResponseDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.CustomerDashboardOrderDetailsDTO;
import com.B2B.EcommerceApp.OrderService.dto.response.SupplierDashboardPurchaseOrdersDTO;
import com.B2B.EcommerceApp.OrderService.service.OrderService;
import com.B2B.EcommerceApp.OrderService.util.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(
            path = "/get-orders-by-customer",
            params = {
                    "sysco_id",
                    "order_status",
                    "page",
                    "size"
            }
    )
    public ResponseEntity<StandardResponse> getAllOrdersByCustomer(
            @RequestParam(value = "sysco_id") String customerSyscoID,
            @RequestParam(value = "order_status") String orderStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedCustomerDashboardOrdersDTO orders = orderService.getAllOrdersByCustomerAndOrderStatus(customerSyscoID, orderStatus, page, size);
        return new ResponseEntity<>(
                new StandardResponse(200, "found successfully", orders), HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-orders-by-supplier",
            params = {
                    "sysco_id",
                    "supply_status",
                    "page",
                    "size"
            }
    )
    public ResponseEntity<StandardResponse> getAllOrdersBySupplier(
            @RequestParam(value = "sysco_id") String supplierSyscoID,
            @RequestParam(value = "supply_status") boolean supplyStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedSupplierDashboardPurchaseOrdersDTO purchaseOrders = orderService.getAllOrdersBySupplierAndOrderStatus(supplierSyscoID, supplyStatus, page, size);
        return new ResponseEntity<>(
                new StandardResponse(200, "found successfully", purchaseOrders), HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-purchase-order-details",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getPurchaseOrderById(@RequestParam(value = "id") int orderID) {
        SupplierDashboardPurchaseOrdersDTO purchaseOrder = orderService.getPurchaseOrderById(orderID);
        return new ResponseEntity<>(
                new StandardResponse(200, "found successfully", purchaseOrder), HttpStatus.OK);
    }

    @GetMapping(
            path = "/get-order-details",
            params = "id"
    )
    public ResponseEntity<StandardResponse> getOrderById(@RequestParam(value = "id") int ID) {
        CustomerDashboardOrderDetailsDTO order = orderService.getCustomerOrderById(ID);
        return new ResponseEntity<>(
                new StandardResponse(200, "found successfully", order), HttpStatus.OK);
    }

    @PutMapping(
            path = "/update-order-by-customer",
            params = "id"
    )
    public ResponseEntity<StandardResponse> updateOrderStatus(@RequestParam(value = "id") int ID, @RequestBody OrderDetailsUpdateDTO orderDetailsUpdateDTO) {
        OrderDTO orderDTO = orderService.updateOrderByCustomer(ID, orderDetailsUpdateDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "updated successfully", orderDTO), HttpStatus.NO_CONTENT);
    }

    @PutMapping(
            path = "/update-supply-status-by-supplier",
            params = "id"
    )
    public ResponseEntity<StandardResponse> updateSupplyStatus(@RequestParam(value = "id") int ID, @RequestBody OrderStatusUpdateDTO orderStatusUpdateDTO) {
        OrderDetailsDTO orderDetailsDTO = orderService.updateSupplyStatusByID(ID, orderStatusUpdateDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "updated successfully", orderDetailsDTO), HttpStatus.NO_CONTENT);
    }


    @PostMapping(
            params = "sessionID"
    )
    public ResponseEntity<StandardResponse> saveOrder(@RequestParam(value = "sessionID") String sessionID, @RequestBody OrderSaveDTO orderSaveDTO) {
        OrderSaveDTO orderSaveDTO1 = orderService.saveOrder(orderSaveDTO, sessionID);
        return new ResponseEntity<>(
                new StandardResponse(201, "saved successfully", orderSaveDTO1), HttpStatus.CREATED
        );
    }

    @PostMapping(
            path = "/cart",
            params = "sessionID"
    )
    public ResponseEntity<StandardResponse> saveCart(@RequestParam(value = "sessionID") String sessionID, @RequestBody CartSaveDTO cartSaveDTO) {
        CartResponseDTO cartResponseDTO = orderService.saveCart(cartSaveDTO, sessionID);
        return new ResponseEntity<>(
                new StandardResponse(201, "saved successfully", cartResponseDTO), HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = "/cart",
            params = "sessionID"
    )
    public ResponseEntity<StandardResponse> getCart(@RequestParam(value = "sessionID") String sessionID) {
        CartResponseDTO cartResponseDTO = orderService.getCartDetails(sessionID);
        return new ResponseEntity<>(
                new StandardResponse(200, "Found successfully", cartResponseDTO), HttpStatus.OK
        );
    }

    @PutMapping(
            path = "/cart",
            params = "sessionID"
    )
    public ResponseEntity<StandardResponse> updateCart(@RequestParam(value = "sessionID") String sessionID, @RequestBody CartUpdateDTO cartUpdateDTO) {
        CartResponseDTO cartResponseDTO = orderService.updateCart(sessionID, cartUpdateDTO);
        return new ResponseEntity<>(
                new StandardResponse(204, "updated successfully", cartResponseDTO), HttpStatus.OK
        );
    }

    @DeleteMapping(
            path = "/cart",
            params = {
                    "sessionID",
                    "id"
            }
    )
    public ResponseEntity<StandardResponse> DeleteCart(@RequestParam(value = "sessionID") String sessionID, @RequestParam(value = "id") int cartID) {
        CartResponseDTO cartResponseDTO = orderService.deleteCart(sessionID, cartID);
        return new ResponseEntity<>(
                new StandardResponse(204, "updated successfully", cartResponseDTO), HttpStatus.OK
        );
    }

}

