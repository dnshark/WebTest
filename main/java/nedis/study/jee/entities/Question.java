package nedis.study.jee.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the question database table.
 */
@Entity
@Table(name = "question")
public class Question extends AbstractEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "QUESTION_IDQUESTION_GENERATOR", sequenceName = "QUESTION_SEQ")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "QUESTION_IDQUESTION_GENERATOR")
    @Column(name = "id_question", unique = true, nullable = false)
    private Long idQuestion;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private Timestamp created;

    @Column(nullable = false, length = 2147483647)
    private String name;

    @Column(nullable = false, columnDefinition = "int default 30")
    private Integer time;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer weight;

    private Timestamp updated;

    //bi-directional many-to-one association to Answer
    @OneToMany(mappedBy = "question")
    @OrderBy("idAnswer")
    private List<Answer> answers;

    //bi-directional many-to-one association to Test
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_test", nullable = false)
    private Test test;

    public Question() {
    }

    public Long getIdQuestion() {
        return this.idQuestion;
    }

    @Override
    @Transient
    public Serializable getId() {
        return getIdQuestion();
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
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

    public String getName() {
        return this.name;
    }

    public void setName(String question) {
        this.name = question;
    }

    public Timestamp getUpdated() {
        return this.updated;
    }

    public void setUpdated(Timestamp updated) {
        this.updated = updated;
    }

    public List<Answer> getAnswers() {
        return this.answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Test getTest() {
        return this.test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }
}