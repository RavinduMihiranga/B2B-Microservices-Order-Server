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
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_ID", length = 10, updatable = false)
    private int cartID;

    @Column(name = "session_ID", length = 200, nullable = false)
    private String sessionID;

    @Column(name = "product_sysco_ID", length = 50, nullable = false)
    private String productSyscoID;

    @Column(name = "product_name", length = 50, nullable = false)
    private String productName;

    @Column(name = "quantity", length = 10, nullable = false)
    private float quantity;

    @Column(name = "price", length = 10, nullable = false)
    private float price;

}
