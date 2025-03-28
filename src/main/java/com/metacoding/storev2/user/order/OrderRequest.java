package com.metacoding.storev2.user.order;

import lombok.Data;

public class OrderRequest {

    @Data
    public static class OrderDTO {
        private Integer productId;
        private String buyer;
        private Integer qty;

    }

}
