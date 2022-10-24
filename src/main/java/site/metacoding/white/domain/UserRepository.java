package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IoC 등록
public class UserRepository {

    // DI
    private final EntityManager em;

    public void save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush (트랜잭션 종료시)
        em.persist(user);
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
