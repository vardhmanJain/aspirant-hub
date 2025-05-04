package dev.vardhman.service;

import dev.vardhman.dto.QuestionDTO;
import dev.vardhman.enums.QuestionType;
import dev.vardhman.model.Answer;
import dev.vardhman.model.MultipleChoiceQuestion;
import dev.vardhman.model.Question;
import dev.vardhman.model.Topic;
import dev.vardhman.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuestionService {
    @Inject
    EntityManager em;

    @Transactional
    public Question create(QuestionDTO dto) {
        Question question;
        if (dto.getqType().equals(QuestionType.MCQ)) {
            question = new MultipleChoiceQuestion();
            question.setQuestionStatement(dto.questionStatement);
            question.setTopic(em.find(Topic.class, dto.topicId));
            question.setUser(em.find(User.class, dto.userId));
            question.setAnswer(new Answer(dto.answer));
            em.persist(question);
        } else {
            question = new MultipleChoiceQuestion();
        }
        return question;
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
