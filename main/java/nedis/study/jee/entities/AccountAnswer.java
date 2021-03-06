package nedis.study.jee.entities;

import javax.persistence.*;
import java.io.Serializable;


/**
 * The persistent class for the account_answer database table.
 */
@Entity
@Table(name = "account_answer")
public class AccountAnswer extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "ACCOUNT_ANSWER_IDACCOUNTANSWER_GENERATOR", sequenceName = "answer_id_answer_seq")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ACCOUNT_ANSWER_IDACCOUNTANSWER_GENERATOR")
    @Column(name = "id_account_answer", unique = true, nullable = false)
    private Long idAccountAnswer;

    //bi-directional many-to-one association to Answer
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_answer", nullable = false)
    private Answer answer;

    //bi-directional many-to-one association to TestResult
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_passed_test", nullable = false)
    private TestResult testResult;

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

    public TestResult getTestResult() {
        return this.testResult;
    }

    public void setTestResult(TestResult testResult) {
        this.testResult = testResult;
    }

}