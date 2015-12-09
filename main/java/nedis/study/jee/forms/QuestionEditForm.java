package nedis.study.jee.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 09.12.2015.
 */
public class QuestionEditForm implements IForm {
    private Long questionId;

    private String questionName;

    private ArrayList<String> cbItemList;   //NEDIS спросить как тут заполнять эти пропуски.
    private ArrayList<String> answerName;
    private ArrayList<String> answerId;

    public ArrayList<String> getAnswerName() {
        return answerName;
    }

    public void setAnswerName(ArrayList<String> answerName) {
        this.answerName = answerName;
    }

    public ArrayList<String> getAnswerId() {
        return answerId;
    }

    public void setAnswerId(ArrayList<String> answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public ArrayList<String> getCbItemList() {
        return cbItemList;
    }

    public void setCbItemList(ArrayList<String> cbItemList) {
        this.cbItemList = cbItemList;
    }
}
