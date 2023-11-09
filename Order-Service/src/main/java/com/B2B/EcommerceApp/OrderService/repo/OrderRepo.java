package com.B2B.EcommerceApp.OrderService.repo;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import com.B2B.EcommerceApp.OrderService.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface OrderRepo extends JpaRepository<Order, Integer> {
    Page<Order> findAllByCustomerSyscoIDEqualsAndOrderStatusEqualsOrderByIDDesc(String customerSyscoID, OrderStatus orderStatus1, Pageable pageable);

    long countAllByCustomerSyscoIDEqualsAndOrderStatusEquals(String customerSyscoID, OrderStatus orderStatus1);
//    Order findByID(int ID);
//
//
//    boolean existsByID(int id);
//
//    List<Order> findAllByCustomerSyscoIDAndOrderStatus(String customerSyscoID, OrderStatus orderStatus1);
//
//    List<Order> findAllBySupplierSyscoIDAndOrderStatus(String supplierSyscoID, OrderStatus orderStatus1);
}
