package nedis.study.jee.services;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.QuestionEditForm;
import nedis.study.jee.forms.TestForm;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface TutorService {

    List<Test> getTestList(Account account);

    void createTest(Test test);

    Test getTest(String testId);

    Question getQuestion(Long questionId);

    void updateQuestion(QuestionEditForm form, Long questionId);

    void deleteQuestion(Long aLong);

    void deleteAnswer(Long aLong);
}
