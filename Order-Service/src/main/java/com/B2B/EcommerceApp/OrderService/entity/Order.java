package com.B2B.EcommerceApp.OrderService.entity;

import com.B2B.EcommerceApp.OrderService.entity.Enum.OrderStatus;
import com.vladmihalcea.hibernate.type.json.JsonType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_ID", length = 10, updatable = false)
    private int ID;

    @Column(name = "customer_sysco_ID", length = 50, nullable = false)
    private String customerSyscoID;

    @Column(name = "customer_name", length = 50, nullable = false)
    private String customerName;

    @Column(name = "total_price", length = 10, nullable = false)
    private float totalPrice;

    @Column(name = "delivery_address", length = 50, nullable = false)
    private String deliveryAddress;

    @Column(name = "delivery_date", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status", length = 20, nullable = false)
    private OrderStatus orderStatus = OrderStatus.valueOf("placed");

    @OneToMany(mappedBy = "orders")
    private Set<OrderDetails> orderDetailsSet;

    public Order(String customerSyscoID, String customerName, float totalPrice, String deliveryAddress, Date deliveryDate, OrderStatus orderStatus) {
        this.customerSyscoID = customerSyscoID;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
        this.deliveryAddress = deliveryAddress;
        this.deliveryDate = deliveryDate;
        this.orderStatus = orderStatus;
    }
}
