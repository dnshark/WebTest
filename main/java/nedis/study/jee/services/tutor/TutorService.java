package nedis.study.jee.services.tutor;

import nedis.study.jee.controllers.tutor.TutorController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.tutor.NewAnswerForm;
import nedis.study.jee.forms.tutor.QuestionEditForm;
import nedis.study.jee.forms.tutor.TestForm;
import nedis.study.jee.forms.util.StringId;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface TutorService {

    List<Test> getTestList(Account account,int page,int count);

    Test createTest(TestForm test);

    Test getTest(Long testId);

    Question getQuestion(Long questionId);

    Question updateQuestion(QuestionEditForm form, Account account) throws Exception;

    Test deleteQuestion(Long aLong,Account account) throws Exception;

    void deleteAnswer(Long aLong, Account account) throws Exception;

    void deleteTest(Long testId, Account account) throws Exception;

    Answer addAnswer(NewAnswerForm newAnswerForm, Account account) throws Exception;

    Question addQuestion(QuestionEditForm form, Account account) throws Exception;

    Test updateTest(TestForm form, Account account) throws Exception;

    QuestionEditForm getQuestionEditForm(Long testId);

    QuestionEditForm fillQuestionEditForm(Question question);

    Boolean checkPermission(Test test, Account account) throws Exception;

    String getHelo();

    TestForm getTestForm(Long testId,Integer page,Integer count);

    int getQuestionMaxPageCount(Long testId, Integer count);

    int getTestMaxPageCount(Account account, Integer count);

    List<StringId> getTests(Integer page, Integer count, Account account);
}