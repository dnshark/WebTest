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
 * The persistent class for the passed_test database table.
 * 
 */
@Entity
@Table(name="test_result")
public class TestResult extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="PASSED_TEST_IDPASSEDTEST_GENERATOR", sequenceName="test_result_id_test_result_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="PASSED_TEST_IDPASSEDTEST_GENERATOR")
	@Column(name="id_passed_test", unique=true, nullable=false)
	private Long idPassedTest;

	@Column(name="correct_answer",nullable = false)
	private int correctAnswer;

	public int getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(int correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	@Column(nullable=false)
	private Timestamp created;

	//bi-directional many-to-one association to Test
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_test", nullable=false)
	private Test test;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	/*
        //bi-directional many-to-one association to AccountAnswer
        @OneToMany(mappedBy="passedTest")
        private List<AccountAnswer> accountAnswers;
    */
	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_account")
	private Account account;

    public TestResult() {
    }

	public Long getIdPassedTest() {
		return this.idPassedTest;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdPassedTest();
	}

	public void setIdPassedTest(Long idPassedTest) {
		this.idPassedTest = idPassedTest;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	/*public List<AccountAnswer> getAccountAnswers() {
		return this.accountAnswers;
	}

	public void setAccountAnswers(List<AccountAnswer> accountAnswers) {
		this.accountAnswers = accountAnswers;
	}*/
	
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}
	
}