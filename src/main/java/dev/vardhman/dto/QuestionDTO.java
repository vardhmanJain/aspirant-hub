package dev.vardhman.dto;

import dev.vardhman.enums.QuestionType;

public class QuestionDTO {
    public Long id;
    public String questionStatement;
    public String answer;
    public Long userId;
    public Long topicId;
    public QuestionType qType;

    public QuestionDTO(Long id, String questionStatement, String answer, Long userId, Long topicId, QuestionType type) {
        this.id = id;
        this.questionStatement = questionStatement;
        this.answer = answer;
        this.userId = userId;
        this.topicId = topicId;
        this.qType = type;
    }

    public QuestionType getqType() {
        return qType;
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

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public void setqType(QuestionType qType) {
        this.qType = qType;
    }

}
