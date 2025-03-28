package com.metacoding.storev2.store;

import lombok.Data;

public class StoreRequest {

    @Data
    public static class saveDTO {
        private String name;
        private Integer stock;
        private Integer price;
    }

    @Data
    public static class UpdateDTO {
        private String name;
        private Integer stock;
        private Integer price;
    }
}
