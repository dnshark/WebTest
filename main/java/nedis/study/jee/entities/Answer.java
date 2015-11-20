package nedis.study.jee.entities;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@Table(name="answer")
public class Answer extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ANSWER_IDANSWER_GENERATOR", sequenceName="ANSWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ANSWER_IDANSWER_GENERATOR")
	@Column(name="id_answer", unique=true, nullable=false)
	private Long idAnswer;

	@Column(nullable=false)
	private Boolean active;

	@Column(nullable=false, length=2147483647)
	private String answer;

	@Column(nullable=false)
	private Boolean correct;

	@Column(nullable=false)
	private Timestamp created;

	@Column
	private Timestamp updated;
/*
	//bi-directional many-to-one association to AccountAnswer
	@OneToMany(mappedBy="answer")
	private List<AccountAnswer> accountAnswers;
*/
	//bi-directional many-to-one association to Question
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_question", nullable=false)
	private Question question;

    public Answer() {
    }

	public Long getIdAnswer() {
		return this.idAnswer;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdAnswer();
	}

	public void setIdAnswer(Long idAnswer) {
		this.idAnswer = idAnswer;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getAnswer() {
		return this.answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Boolean getCorrect() {
		return this.correct;
	}

	public void setCorrect(Boolean correct) {
		this.correct = correct;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	/*public List<AccountAnswer> getAccountAnswers() {
		return this.accountAnswers;
	}

	public void setAccountAnswers(List<AccountAnswer> accountAnswers) {
		this.accountAnswers = accountAnswers;
	}*/
	
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
	
}