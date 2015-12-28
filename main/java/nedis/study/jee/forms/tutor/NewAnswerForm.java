package nedis.study.jee.forms.tutor;

import nedis.study.jee.forms.IForm;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class NewAnswerForm implements IForm {
    private Long testId;

    private Long questionId;

    private Boolean correct;

    private String name;

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }
}
