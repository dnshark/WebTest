package nedis.study.jee.forms.student;

import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.forms.IForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrij on 24.11.2015.
 */
public class TestPassForm implements IForm {

    private Question question;

    private List<Answer> answers;

    private Integer timePerQuestion;

    private ArrayList<String> answer;

    public ArrayList<String> getAnswer() {

        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(Integer timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }
}
