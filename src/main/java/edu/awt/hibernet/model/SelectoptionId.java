package edu.awt.hibernet.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class SelectoptionId {
	
	@ManyToOne
	@JoinColumn(name="qno")
	private Question question;
	
	@ManyToOne
	@JoinColumn(name="sno")
	private Option option;

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}


}
