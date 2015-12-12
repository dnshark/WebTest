package nedis.study.jee.services;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.NewAnswerForm;
import nedis.study.jee.forms.QuestionEditForm;
import nedis.study.jee.forms.TestForm;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface TutorService {

    List<Test> getTestList(Account account,int offSet,int count);

    Test createTest(TestForm test);

    Test getTest(Long testId);

    Question getQuestion(Long questionId);

    Question updateQuestion(QuestionEditForm form, Long questionId);

    Test deleteQuestion(Long aLong);

    void deleteAnswer(Long aLong);

    void deleteTest(Long testId);

    Answer addAnswer(NewAnswerForm newAnswerForm);

    Question addQuestion(QuestionEditForm form);

    Test updateTest(TestForm form);

    QuestionEditForm getQuestionEditForm(Long testId);

    QuestionEditForm fillQuestionEditForm(Question question);
}
