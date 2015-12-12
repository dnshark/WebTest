package nedis.study.jee.services;

import nedis.study.jee.entities.*;
import nedis.study.jee.forms.AnswerForm;
import nedis.study.jee.services.impl.utils.TestSessionInfo;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface StudentService {

    List<Test> listAllTests(int offSet,int count);

    List<TestResult> listAllResult(Account account,int offSet,int count);

    Test getTestById(long testId);

    Question getQuestionByNumber(Long testId, Integer number);

    Integer checkCorrectAnswer(Answer answer, ArrayList<String> userAnswers);

    Integer checkCorrectAnswers(List<Answer> answers, ArrayList<String> userAnswers);

    TestResult saveResult(Account current_account, TestSessionInfo testSessionInfo);

    AnswerForm doAnswer(HttpSession session, AnswerForm form, Account account);


    TestSessionInfo initTestSessionInfo(Long testId);
}
