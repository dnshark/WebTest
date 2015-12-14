package nedis.study.jee.services.student;

import nedis.study.jee.entities.*;
import nedis.study.jee.forms.student.TestPassForm;

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

    TestPassForm doAnswer(HttpSession session, TestPassForm form, Account account);

    TestPassForm getTestPassForm(Account account, TestSessionInfo testSessionInfo);

    TestSessionInfo initTestSessionInfo(Long testId);
}
