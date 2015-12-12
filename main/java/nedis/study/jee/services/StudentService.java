package nedis.study.jee.services;

import nedis.study.jee.entities.*;
import nedis.study.jee.forms.AnswerForm;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface StudentService {

    List<Test> listAllTests();

    List<TestResult> listAllResult(Account account);

    Test getTestById(long testId);

    List<Answer> getAnswers(Question question);

    Question getQuestionById(long questionId);

    Question getQuestionByNumber(Long testId, Integer number);

    Integer checkCorrectAnswer(Answer answer, ArrayList<String> userAnswers);

    Integer checkCorrectAnswers(List<Answer> answers, ArrayList<String> userAnswers);

    TestResult saveResult(Account current_account, String current_test, int correct_answer);

    Question doNextQuestion(HttpSession session, AnswerForm form);

    Object getTimePerQuestion(HttpSession session);
}
