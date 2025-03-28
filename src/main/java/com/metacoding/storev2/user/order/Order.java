package com.metacoding.storev2.user.order;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Table(name = "order_tb")
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer storeId;
    private Integer userId;
    private Integer qty;
    private Integer totalPrice;
}
