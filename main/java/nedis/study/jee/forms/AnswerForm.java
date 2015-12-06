package nedis.study.jee.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dmitrij on 24.11.2015.
 */
public class AnswerForm implements IForm{

    private ArrayList<String> answer;

    public ArrayList<String> getAnswer() {

        return answer;
    }

    public void setAnswer(ArrayList<String> answer) {
        this.answer = answer;
    }

}
