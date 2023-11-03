package com.github.Shop.order;

import com.github.Shop.customer.Customer;
import com.github.Shop.orderrow.OrderRow;
import com.github.Shop.product.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Table(name = "'order'")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime placeDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<OrderRow> orderRows;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<Product> products;
    private BigDecimal grossValue;
    @OneToMany
    @JoinColumn(name = "orderId")
    private List<Customer> customers;
}