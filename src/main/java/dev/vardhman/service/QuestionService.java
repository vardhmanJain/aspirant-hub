package dev.vardhman.service;

import java.util.List;

import dev.vardhman.dto.QuestionDTO;
import dev.vardhman.model.Question;
import dev.vardhman.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuestionService {
    @Inject
    EntityManager em;

    @Transactional
    public QuestionDTO create(Question question, Long userId) {
        User user = em.find(User.class, userId);
        question.setUser(user);
        em.persist(question);
        return new QuestionDTO(question);
    }

    public List<Question> getAll(Long userId) {
        User user = em.find(User.class, userId);
        Query query = em.createQuery("Select q from Question q where q.user=:user");
        query.setParameter("user", user);
        List<Question> questions = query.getResultList();
        return questions.size() < 1 ? null : questions;
    }

    public Question get(Long id) {
        Question question = em.find(Question.class, id);
        return question;
    }

    public Question delete(Long id) {
        Question question = em.find(Question.class, id);
        em.remove(question);
        return question;
    }
}
