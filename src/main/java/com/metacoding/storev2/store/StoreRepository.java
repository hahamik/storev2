package com.metacoding.storev2.store;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class StoreRepository {

    private final EntityManager em;


    public void save(String name, Integer stock, Integer price) {
        Query query = em.createNativeQuery("INSERT INTO store_tb(name, stock, price) VALUES (?, ?, ?)");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.executeUpdate();
    }

    public void update(String name, Integer stock, Integer price, int productId) {
        Query query = em.createNativeQuery("UPDATE store_tb SET name = ?, stock = ?, price = ? WHERE id = ?");
        query.setParameter(1, name);
        query.setParameter(2, stock);
        query.setParameter(3, price);
        query.setParameter(4, productId);
        query.executeUpdate();
    }

    public void delete(int productId) {
        Query query = em.createNativeQuery("DELETE FROM store_tb WHERE id = ?");
        query.setParameter(1, productId);
        query.executeUpdate();
    }

    public Store findByProductId(int productId) {
        Query query = em.createNativeQuery("SELECT id, name, stock, price FROM store_tb WHERE id = ?");
        query.setParameter(1, productId);

        try {
            Object[] ob = (Object[]) query.getSingleResult();
            return new Store(
                    (int) ob[0],
                    (String) ob[1],
                    (int) ob[2],
                    (int) ob[3]
            );
        } catch (Exception e) {
            return null;
        }
    }


    public List<StoreResponse.ListPageDTO> findAll() {
        List<StoreResponse.ListPageDTO> storeList = new ArrayList<>();

        Query query = em.createNativeQuery("SELECT id, name FROM store_tb ORDER BY id DESC");
        List<Object[]> obsList = query.getResultList();

        for (Object[] obs : obsList) {
            StoreResponse.ListPageDTO store = new StoreResponse.ListPageDTO(
                    (int) obs[0],
                    (String) obs[1]
            );
            storeList.add(store);
        }

        return storeList;
    }


    public Store detail(int productId) {
        Query query = em.createNativeQuery("SELECT id,name, stock,price FROM store_tb WHERE id = ?");
        query.setParameter(1, productId);

        Object[] ob = (Object[]) query.getSingleResult();

        Store store = new Store
                (
                        (int) ob[0],
                        (String) ob[1],
                        (int) ob[2],
                        (int) ob[3]
                );

        return store;
    }

}
