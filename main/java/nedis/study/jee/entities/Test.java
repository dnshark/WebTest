package nedis.study.jee.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the test database table.
 */
@Entity
@Table(name = "test")
public class Test extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "TEST_IDTEST_GENERATOR", sequenceName = "TEST_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "TEST_IDTEST_GENERATOR")
    @Column(name = "id_test", unique = true, nullable = false)
    private Long idTest;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Timestamp created;

    @Column(length = 2147483647)
    private String description;

    @Column(name = "time_per_question", nullable = false)
    private Integer timePerQuestion;

    @Column(nullable = false, length = 100)
    private String name;

    private Timestamp updated;

    /*
    //bi-directional many-to-one association to TestResult
    @OneToMany(mappedBy="test")
    private List<TestResult> passedTests;
*/
    //bi-directional many-to-one association to Question
    @OneToMany(mappedBy = "test")
    @OrderBy("created")
    private List<Question> questions;

    //bi-directional many-to-one association to Account
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_account", nullable = false)
    private Account account;

    public Test() {
    }

    public Long getIdTest() {
        return this.idTest;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdTest();
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Timestamp getCreated() {
        return this.created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTimePerQuestion() {
        return this.timePerQuestion;
    }

    public void setTimePerQuestion(Integer periodPerQuestion) {
        this.timePerQuestion = periodPerQuestion;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String title) {
        this.name = title;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

	/*public List<TestResult> getPassedTests() {
        return this.passedTests;
	}

	public void setPassedTests(List<TestResult> passedTests) {
		this.passedTests = passedTests;
	}*/

    public List<Question> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}