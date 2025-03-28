package com.metacoding.storev2.store;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreRepository storeRepository;


    @Transactional
    public void 상품등록(StoreRequest.saveDTO saveDTO) {
        storeRepository.save(saveDTO.getName(), saveDTO.getStock(), saveDTO.getPrice());
    }

    @Transactional
    public void 상품수정(StoreRequest.UpdateDTO updateDTO, int productId) {
        // 존재하는 상품???
        Store store = storeRepository.findByProductId(productId);
        if (store == null) throw new RuntimeException("존재하지 않는 상품입니다.");
        // 업뎃
        storeRepository.update(updateDTO.getName(), updateDTO.getStock(), updateDTO.getPrice(), productId);
    }

    @Transactional
    public void 상품삭제(int productId) {
        // 존재하는 상품???
        Store store = storeRepository.findByProductId(productId);
        if (store == null) throw new RuntimeException("존재하지 않는 상품입니다.");
        // 삭제
        storeRepository.delete(productId);
    }


    public List<StoreResponse.ListPageDTO> 상품목록() {
        List<StoreResponse.ListPageDTO> storeList = storeRepository.findAll();
        return storeList;
    }

    public Store 상품상세(int productId) {
        Store store = storeRepository.detail(productId);
        return store;
    }
}
