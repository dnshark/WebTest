package nedis.study.jee.forms;

import java.util.List;

/**
 * Created by Дмитрий on 09.12.2015.
 */
public class QuestionEditForm implements IForm {
    private Long questionId;

    private String questionName;

    private List<String> cbItemList;

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

    public List<String> getCbItemList() {
        return cbItemList;
    }

    public void setCbItemList(List<String> cbItemList) {
        this.cbItemList = cbItemList;
    }
}
