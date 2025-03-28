package com.metacoding.storev2.user.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class OrderRepository {

    private final EntityManager em;

    public void save(int storeId, int userId, int qty, int totalPrice) {
        Query query = em.createNativeQuery("INSERT INTO order_tb(store_id, user_id, qty, total_price) VALUES(?,?,?,?)");
        query.setParameter(1, storeId);
        query.setParameter(2, userId);
        query.setParameter(3, qty);
        query.setParameter(4, totalPrice);
        query.executeUpdate();
    }

    public void updateStock(int storeId, int updateStock) {
        Query query = em.createNativeQuery("UPDATE  store_tb SET stock = ? WHERE store_id = ??");
        query.setParameter(1, updateStock);
        query.setParameter(2, storeId);
        query.executeUpdate();
    }

    public List<OrderResponse.OrderDTO> orderList() {
        List<OrderResponse.OrderDTO> orderList = new ArrayList<>();
        Query query = em.createNativeQuery("""
                SELECT ot.id, st.name,ot.qty,ot.total_price
                FROM order_tb ot
                INNER JOIN store_tb st
                ON ot.store_id = st.id;
                """);
        List<Object[]> obsList = query.getResultList();

        for (Object[] obs : obsList) {
            orderList.add(new OrderResponse.OrderDTO(
                    (int) obs[0],
                    ((String) obs[1]),
                    ((int) obs[2]),
                    ((int) obs[3])
            ));
        }
        return orderList;
    }
}
