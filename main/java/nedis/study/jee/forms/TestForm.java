package nedis.study.jee.forms;

import java.util.List;

/**
 * Created by Dmitrij on 24.11.2015.
 */
public class TestForm {
    private int numberQuestion;

    private List<Boolean> answer;

    private int correctAnswer;

    TestForm() {
        setCorrectAnswer(0);
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<Boolean> getAnswer() {

        return answer;
    }

    public void setAnswer(List<Boolean> answer) {
        this.answer = answer;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

}
