package nedis.study.jee.forms;

import nedis.study.jee.forms.util.StringId;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 02.12.2015.
 */
public class TestForm implements IForm{

    private Long id;

    private String name;

    private String timePerQuestion;

    private String description;

    private ArrayList<StringId> question;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(String timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StringId> getQuestion() {
        return question;
    }

    public void setQuestion(ArrayList<StringId> question) {
        this.question = question;
    }
}
