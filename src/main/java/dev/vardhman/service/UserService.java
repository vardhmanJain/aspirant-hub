package dev.vardhman.service;

import java.util.List;

import dev.vardhman.dto.UserDTO;
import dev.vardhman.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {
    @Inject
    EntityManager em;

    public UserService() {
    }

    @Transactional
    public UserDTO create(UserDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        em.persist(user);
        return new UserDTO(user);
    }

    public UserDTO get(Long id) {
        User user = em.find(User.class, id);
        return new UserDTO(user);
    }

    public UserDTO get(UserDTO user) {
        Query query = em.createQuery("Select u from User u where u.email=:email and u.password=:password");
        query.setParameter("email", user.getEmail());
        query.setParameter("password", user.getPassword());
        List<User> savedUser = query.getResultList();
        return new UserDTO(savedUser.get(0));
    }

    public UserDTO delete(Long id) {
        User user = em.find(User.class, id);
        em.remove(user);
        return new UserDTO(user);
    }
}
