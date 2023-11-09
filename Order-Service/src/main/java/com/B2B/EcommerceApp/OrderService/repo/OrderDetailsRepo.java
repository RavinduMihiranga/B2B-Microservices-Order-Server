package com.B2B.EcommerceApp.OrderService.repo;

import com.B2B.EcommerceApp.OrderService.dto.queryInterfaces.SupplierDashboardPurchaseOrdersInterface;
import com.B2B.EcommerceApp.OrderService.entity.Order;
import com.B2B.EcommerceApp.OrderService.entity.OrderDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface OrderDetailsRepo extends JpaRepository<OrderDetails, Integer> {
    int countAllByOrders(Order order);

    @Query(
            value = "SELECT od.order_details_id as orderDetailsID, od.product_name as productName, od.product_sysco_id as productSyscoID, od.quantity as quantity, o.customer_sysco_id as customerSyscoID, o.customer_name as customerName, o.delivery_date as deliveryDate, o.delivery_address as deliveryAddress from order_details od, orders o where od.supply_status = ?2 and od.supplier_sysco_id = ?1 and od.order_id = o.order_id",
            nativeQuery = true
    )
    List<SupplierDashboardPurchaseOrdersInterface> getAllPurchaseOrders(String supplierSyscoID, boolean supplyStatus, Pageable pageable);

    @Query(
            value = "SELECT count(*) from order_details od, orders o where od.supply_status = ?2 and od.supplier_sysco_id = ?1 and od.order_id = o.order_id",
            nativeQuery = true
    )
    long countAllPurchaseOrders(String supplierSyscoID, boolean supplyStatus);

    @Query(
            value = "SELECT od.order_details_id as orderDetailsID, od.product_name as productName, od.product_sysco_id as productSyscoID, od.quantity as quantity, o.customer_sysco_id as customerSyscoID, o.customer_name as customerName, o.delivery_date as deliveryDate, o.delivery_address as deliveryAddress from order_details od, orders o where  od.order_details_id = ?1 and od.order_id = o.order_id",
            nativeQuery = true
    )
    SupplierDashboardPurchaseOrdersInterface getPurchaseOrderById(int orderDetailsID);

    List<OrderDetails> findAllByOrdersOrderByID(Order order);

    ;
}
