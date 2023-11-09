package com.B2B.EcommerceApp.OrderService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_details_ID", length = 10, updatable = false)
    private int ID;

    @Column(name = "product_sysco_ID", length = 50, nullable = false)
    private String productSyscoID;

    @Column(name = "product_name", length = 50, nullable = false)
    private String productName;

    @Column(name = "quantity", length = 10, nullable = false)
    private float quantity;

    @Column(name = "price", length = 10, nullable = false)
    private float price;

    @Column(name = "supplier_sysco_ID", length = 50, nullable = false)
    private String supplierSyscoID;

    @Column(name = "supplier_name", length = 50, nullable = false)
    private String supplierName;

    @Column(name = "supply_status", length = 50, nullable = false)
    private boolean supplyStatus = false;

    @ManyToOne
    @JoinColumn(name = "order_ID", nullable = false)
    private Order orders;


    public OrderDetails(String productSyscoID, String productName, float quantity, float price, String supplierSyscoID, String supplierName, Order orders) {
        this.productSyscoID = productSyscoID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.supplierSyscoID = supplierSyscoID;
        this.supplierName = supplierName;
        this.orders = orders;
    }
}
