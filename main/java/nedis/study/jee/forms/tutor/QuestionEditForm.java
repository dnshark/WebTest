package nedis.study.jee.forms.tutor;

import nedis.study.jee.forms.IForm;

import java.util.ArrayList;

/**
 * Created by Дмитрий on 09.12.2015.
 */
public class QuestionEditForm implements IForm {
    private Long testId;

    private Long questionId;

    private String questionName;

    private ArrayList<String> cbItemList;
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

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
