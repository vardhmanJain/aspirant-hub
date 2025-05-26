package dev.vardhman.service;

import java.util.Date;
import java.util.List;

import dev.vardhman.exception.ResourceNotFoundException;
import dev.vardhman.model.Question;
import dev.vardhman.model.Quiz;
import dev.vardhman.model.Topic;
import dev.vardhman.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuizService {
    @Inject
    EntityManager em;

    @Transactional
    public Quiz create(Long userId, List<Long> topicIds, int count) {
        System.out.println("get referenct to user");
        User user = em.find(User.class, userId);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + userId);
        }
        List<Topic> topics = em.createQuery("SELECT t FROM Topic t WHERE t.id IN :topicIds", Topic.class)
                .setParameter("topicIds", topicIds)
                .getResultList();
        System.out.println("create query to fetch questions");
        var query = em.createQuery("SELECT q FROM Question q WHERE q.topic IN :topics ORDER BY function('RANDOM')",
                Question.class);
        query.setMaxResults(count);
        query.setParameter("topics", topics);
        List<Question> questions = query.getResultList();
        Quiz quiz = new Quiz();
        quiz.setUser(user);
        quiz.setTopics(topics);
        quiz.setQuestions(questions);
        quiz.setQuestionCount(count);
        quiz.setCreatedOn(new Date());
        em.persist(quiz);
        return quiz;
    }
}
