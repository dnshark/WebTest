package nedis.study.jee.entities;

import java.io.Serializable;

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
 * The persistent class for the account_answer database table.
 * 
 */
@Entity
@Table(name="account_answer")
public class AccountAnswer extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_ANSWER_IDACCOUNTANSWER_GENERATOR", sequenceName="ACCOUNT_ANSWER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_ANSWER_IDACCOUNTANSWER_GENERATOR")
	@Column(name="id_account_answer", unique=true, nullable=false)
	private Long idAccountAnswer;

	//bi-directional many-to-one association to Answer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_answer", nullable=false)
	private Answer answer;

	//bi-directional many-to-one association to PassedTest
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_passed_test", nullable=false)
	private PassedTest passedTest;

    public AccountAnswer() {
    }

	public Long getIdAccountAnswer() {
		return this.idAccountAnswer;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdAccountAnswer();
	}

	public void setIdAccountAnswer(Long idAccountAnswer) {
		this.idAccountAnswer = idAccountAnswer;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
	
	public PassedTest getPassedTest() {
		return this.passedTest;
	}

	public void setPassedTest(PassedTest passedTest) {
		this.passedTest = passedTest;
	}
	
}