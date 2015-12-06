package nedis.study.jee.forms;

import nedis.study.jee.entities.Answer;

import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
public class TestForm implements IForm{

    private String name;

    private Integer time;

    private List<Answer> answers;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }
}
