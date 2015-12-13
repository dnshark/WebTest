package nedis.study.jee.services.student;

/**
 * Created by Дмитрий on 12.12.2015.
 */
public class TestSessionInfo {

    private Integer correctAnswer;

    private Integer questionNumber;

    private Long testId;

    private Integer timePerQuestion;

    public void clear(Long testId){
        correctAnswer = 0;
        questionNumber = 0;
        this.testId = testId;
        timePerQuestion = 300;
    }

    public Integer incQuestNumber(){
        this.questionNumber++;
        return questionNumber;
    }

    public void incCorrectAnswer(Integer correctAnswer){
        this.correctAnswer +=  correctAnswer;
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

    public Integer getTimePerQuestion() {
        return timePerQuestion;
    }

    public void setTimePerQuestion(Integer timePerQuestion) {
        this.timePerQuestion = timePerQuestion;
    }
}
