package nedis.study.jee.services.student;

/**
 * Created by Дмитрий on 12.12.2015.
 */
public class TestSessionInfo {

    private Integer correctAnswer;

    private Integer questionNumber;

    private Long testId;

    public void clear(Long testId) {
        correctAnswer = 0;
        questionNumber = 0;
        this.testId = testId;
    }

    public Integer incQuestNumber() {
        this.questionNumber++;
        return questionNumber;
    }

    public void incCorrectAnswer(Integer correctAnswer) {
        this.correctAnswer += correctAnswer;
    }

    public Integer getCorrectAnswer() {
        return correctAnswer;
    }

    public Integer getQuestionNumber() {
        return questionNumber;
    }

    public Long getTestId() {
        return testId;
    }
}
