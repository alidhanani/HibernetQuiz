package edu.awt.hibernet.model;
// Generated Jun 4, 2017 8:29:42 PM by Hibernate Tools 4.3.5.Final

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Selectoption generated by hbm2java
 */
@Entity

@AssociationOverrides({
	@AssociationOverride(name="pk.question", joinColumns=@JoinColumn(name="qno")),
	@AssociationOverride(name="pk.option", joinColumns=@JoinColumn(name="sno"))
})

@Table(name = "selectoption", catalog = "mcqs")
public class Selectoption implements java.io.Serializable {
	
	@EmbeddedId
	private SelectoptionId pk = new SelectoptionId();

	private int regno;
	private int correct;

	public Selectoption() {
	}

	public Selectoption(int regno, int qno, int sno, int correct) {
		this.regno = regno;
		this.correct = correct;
	}

	@Id

	@Column(name = "Regno", unique = true, nullable = false)
	public int getRegno() {
		return this.regno;
	}

	public void setRegno(int regno) {
		this.regno = regno;
	}

	@Column(name = "Correct", nullable = false)
	public int getCorrect() {
		return this.correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public SelectoptionId getPk() {
		return pk;
	}

	public void setPk(SelectoptionId pk) {
		this.pk = pk;
	}
	
	@Transient
	public Question getQuestion() {
		return getPk().getQuestion();
	}
	
	public void setQuestion(Question question) {
		getPk().setQuestion(question);
	}
	
	@Transient
	public Option getOption() {
		return getPk().getOption();
	}
	
	public void setOption(Option option) {
		getPk().setOption(option);
	}
	

}
