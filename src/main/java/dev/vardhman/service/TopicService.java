package dev.vardhman.service;

import java.util.List;

import dev.vardhman.model.Question;
import dev.vardhman.model.Topic;
import dev.vardhman.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TopicService {
    @Inject
    EntityManager em;

    @Transactional
    public Topic create(Topic topic) {
        em.persist(topic);
        return topic;
    }

    public List<Topic> getAll() {
        Query query = em.createQuery("Select t from Topic t");
        List<Topic> topics = query.getResultList();
        return topics.size() < 1 ? null : topics;
    }
}
