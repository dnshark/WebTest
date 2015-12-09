package nedis.study.jee.forms;

import nedis.study.jee.entities.Question;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 09.12.2015.
 */
public class QuestionEditForm implements IForm {
    private Question question;

    private ArrayList<String> correct;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public ArrayList<String> getCorrect() {
        return correct;
    }

    public void setCorrect(ArrayList<String> correct) {
        this.correct = correct;
    }
}
