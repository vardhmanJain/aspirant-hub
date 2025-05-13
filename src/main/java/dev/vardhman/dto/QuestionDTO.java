package dev.vardhman.dto;

import dev.vardhman.enums.QuestionType;
import dev.vardhman.model.Question;
import dev.vardhman.model.Topic;

public class QuestionDTO {
    public Long id;
    public String questionStatement;
    public String answer;
    public Long userId;
    public Topic topic;
    public QuestionType type;

    public QuestionDTO(Question question) {
        this.id = question.getId();
        this.questionStatement = question.getQuestionStatement();
        this.answer = question.getAnswer().getAnswerStatement();
        this.topic = question.getTopic();
        this.type = question.getType();
    }

    public QuestionType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "QuestionDTO [id=" + id + ", questionStatement=" + questionStatement + ", answer=" + answer + ", userId="
                + userId + ", topic=" + topic + ", type=" + type + "]";
    }

    QuestionDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionStatement() {
        return questionStatement;
    }

    public void setQuestionStatement(String questionStatement) {
        this.questionStatement = questionStatement;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setType(QuestionType type) {
        this.type = type;
    }

}
