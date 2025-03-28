package com.metacoding.storev2.user.order;

import lombok.AllArgsConstructor;
import lombok.Data;

public class OrderResponse {
    @Data
    @AllArgsConstructor
    public static class OrderDTO {
        private int orderId;
        private String name;
        private Integer qty;
        private Integer totalPrice;
    }
}