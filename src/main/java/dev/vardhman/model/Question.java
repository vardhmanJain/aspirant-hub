package dev.vardhman.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import dev.vardhman.enums.QuestionType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = MultipleChoiceQuestion.class, name = "MCQ"),
		@JsonSubTypes.Type(value = SingleLineAnswerQuestion.class, name = "SINGLE_LINE")
})
public abstract class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(optional = true, fetch = FetchType.EAGER)
	@JoinColumn(name = "topicId")
	private Topic topic;
	@ManyToOne(optional = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "userId")
	@JsonIgnore
	private User user;
	private Answer answer;
	private String questionStatement;
	@Enumerated(EnumType.STRING)
	private QuestionType type;

	public Question() {
	}

	public QuestionType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", topic=" + topic + ", user=" + user + ", answer=" + answer
				+ ", questionStatement=" + questionStatement + ", type=" + type + "]";
	}

	public void setType(QuestionType type) {
		this.type = type;
	}

	public Question(String questionStatement, Topic topic, User user, Answer answer) {
		super();
		this.questionStatement = questionStatement;
		this.topic = topic;
		this.user = user;
		this.answer = answer;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

}
