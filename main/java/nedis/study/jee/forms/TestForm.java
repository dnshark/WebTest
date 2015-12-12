package nedis.study.jee.forms;

import nedis.study.jee.forms.util.StringId;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 02.12.2015.
 */
public class TestForm implements IForm{

    private Long idTest;

    private String name;

    private Integer timePerQuestion;

    private String description;

    private ArrayList<StringId> testQuestions;

    public Long getIdTest() {
        return idTest;
    }

    public void setIdTest(Long idTest) {
        this.idTest = idTest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(Integer timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StringId> getTestQuestions() {
        return testQuestions;
    }

    public void setTestQuestions(ArrayList<StringId> testQuestions) {
        this.testQuestions = testQuestions;
    }
}
