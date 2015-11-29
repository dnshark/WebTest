package nedis.study.jee.forms;

import java.util.List;

/**
 * Created by Dmitrij on 24.11.2015.
 */
public class TestForm implements IForm{

    private List<String> answer;

    public List<String> getAnswer() {

        return answer;
    }

    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }

}
