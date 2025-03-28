package com.metacoding.storev2.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;

    public void save(String username, String password, String fullname) {
        Query query = em.createNativeQuery("INSERT INTO user_tb(username, password, fullname,created_at) VALUES (?, ?, ?,now())");
        query.setParameter(1, username);
        query.setParameter(2, password);
        query.setParameter(3, fullname);
        query.executeUpdate();
    }

    public User findByUsername(String username) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE username = ?", User.class);
        query.setParameter(1, username);
        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    public User login(String username) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE username = ?", User.class);
        query.setParameter(1, username);
        return (User) query.getSingleResult();
    }
}
