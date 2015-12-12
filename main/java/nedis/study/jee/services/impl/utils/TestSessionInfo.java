package nedis.study.jee.services.impl.utils;

import javax.servlet.http.HttpSession;

/**
 * Created by Дмитрий on 12.12.2015.
 */
public class TestSessionInfo {

    private HttpSession session;

    public TestSessionInfo(HttpSession session) {
        this.session = session;
    }

    private Integer correctAnswer;

    private Integer questionNumber;

    private Long testId;

    public void clear(Long testId){
        session.setAttribute("CORRECT_ANSWER", 0);
        session.setAttribute("QUESTION_NUMBER", 0);
        session.setAttribute("CURRENT_TEST",testId);
    }

    public void fillInfo() {
        this.questionNumber = (Integer)session.getAttribute("QUESTION_NUMBER");
        this.correctAnswer = (Integer) session.getAttribute("CORRECT_ANSWER");
        this.testId = (Long)session.getAttribute("CURRENT_TEST");
    }

    public Integer incQuestNumber(){
        this.questionNumber++;
        return questionNumber;
    }

    public void incCorrectAnswer(Integer correctAnswer){
        this.correctAnswer +=  correctAnswer;
    }

    public void saveToSession(){
        session.setAttribute("CORRECT_ANSWER", correctAnswer);
        session.setAttribute("QUESTION_NUMBER", questionNumber);
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
