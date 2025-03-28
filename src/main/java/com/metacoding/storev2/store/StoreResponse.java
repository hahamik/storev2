package com.metacoding.storev2.store;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StoreResponse {
    @Data
    @AllArgsConstructor
    public static class ListPageDTO {
        private int id;
        private String name;
    }

    @Data
    @AllArgsConstructor
    public static class detailDTO {
        private int id;
        private String name;
        private int price;
        private int stock;
    }
}
