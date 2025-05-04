package dev.vardhman.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class MultipleChoiceQuestion extends Question {

	public MultipleChoiceQuestion() {
	}

	public MultipleChoiceQuestion(String questionStatement, Answer answer, Topic topic, User user) {
		super(questionStatement, topic, user, answer);
	}

}
