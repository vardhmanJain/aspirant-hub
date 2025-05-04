package dev.vardhman.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Answer {

	private String answerStatement;

	public Answer() {
		super();
	}

	public Answer(String answerStatement) {
		this.answerStatement = answerStatement;
	}

	public String getAnswerStatement() {
		return answerStatement;
	}

	public void setAnswerStatement(String answerStatement) {
		this.answerStatement = answerStatement;
	}

}
