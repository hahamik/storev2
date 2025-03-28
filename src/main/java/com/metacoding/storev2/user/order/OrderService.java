package com.metacoding.storev2.user.order;

import com.metacoding.storev2.store.Store;
import com.metacoding.storev2.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public void 구매하기(int storeId, int userId, int qty) {
        // 1. 상품 재고 업데이트 (조회, 업데이트)
        Store store = storeRepository.findByProductId(storeId);
        if (store == null) throw new RuntimeException("존재하지 않는 상품");
        storeRepository.update(store.getName(), store.getStock(), store.getPrice(), store.getId());

        if (store.getStock() < qty) throw new RuntimeException("재고가 부족합니다.");
        // 재고 줄이기
        store.재고감소(qty);
        storeRepository.update(store.getName(), store.getStock(), store.getPrice(), store.getId());
        // 2. 구매 기록 하기
        orderRepository.save(storeId, userId, qty, qty * store.getPrice());
    }


    public List<OrderResponse.OrderDTO> 구매목록() {
        List<OrderResponse.OrderDTO> orderList = orderRepository.orderList();
        return orderList;
    }
}
