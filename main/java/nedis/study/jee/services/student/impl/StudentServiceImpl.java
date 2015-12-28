package nedis.study.jee.services.student.impl;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.*;
import nedis.study.jee.forms.student.TestPassForm;
import nedis.study.jee.services.student.StudentService;
import nedis.study.jee.services.student.TestSessionInfo;
import nedis.study.jee.utils.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StudentServiceImpl implements StudentService, ApplicationConstants {

    @Autowired
    @Qualifier("hibernateTestDao")
    private TestDao testDao;

    @Autowired
    @Qualifier("hibernateQuestionDao")
    private QuestionDao questionDao;

    @Autowired
    @Qualifier("hibernateTestResultDao")
    private TestResultDao testResultDao;

    @Autowired
    @Qualifier("hibernateAccountDao")
    private AccountDao accountDao;

    @Autowired
    @Qualifier("entityBuilder")
    private EntityBuilder entityBuilder;

    @Override
    public List<Test> listAllTests(int page, int count) {
        return testDao.getTestList((page - 1) * count, count);
    }

    @Override
    public List<TestResult> listAllResult(Account account, int page, int count) {
        return testResultDao.getUserResults(account, page, count);
    }

    public Test getTestById(long testId) {
        return testDao.findById(testId);
    }

    @Override
    public Question getQuestionByNumber(Long testId, Integer number) {
        Test test = testDao.findById(testId);
        return questionDao.getQuestionByNumber(number, test);
    }

    @Override
    public Integer checkCorrectAnswer(Answer answer, ArrayList<String> userAnswers) {
        String id = String.valueOf(answer.getIdAnswer());

        Boolean exists = userAnswers.contains(id);

        Integer result = 0;

        if (exists && answer.getCorrect()) {
            result = 1;
        }

        if (exists && !answer.getCorrect()) {
            result = -1;
        }

        return result;
    }

    @Override
    public Integer checkCorrectAnswers(List<Answer> answers, ArrayList<String> userAnswers) {
        Integer correct = 0;
        if (userAnswers == null) {
            return correct;
        }

        for (Answer answer : answers) {
            correct = correct + (checkCorrectAnswer(answer, userAnswers));
        }

        if (correct < 0) {
            correct = 0;
        }
        return correct;
    }

    @Override
    @Transactional
    public TestResult saveResult(Account current_account, TestSessionInfo testSessionInfo) {
        Test test = testDao.findById(testSessionInfo.getTestId());
        TestResult testResult = entityBuilder.buildTestResult(current_account, test);
        testResult.setCorrectAnswer(testSessionInfo.getCorrectAnswer());
        testResult.setAllCount(testDao.getCorrectCountAnswer(test));
        testResultDao.save(testResult);
        return testResult;
    }

    @Override
    public TestPassForm doAnswer(TestSessionInfo testSessionInfo, TestPassForm form, Account account) {

        testSessionInfo.incCorrectAnswer(checkCorrectAnswers(form, testSessionInfo));

        testSessionInfo.incQuestNumber();

        return getTestPassForm(account, testSessionInfo);
    }

    public TestPassForm getTestPassForm(Account account, TestSessionInfo testSessionInfo) {
        Question question = getQuestionByNumber(testSessionInfo.getTestId(), testSessionInfo.getQuestionNumber());

        if (question == null) {
            saveResult(account, testSessionInfo);
            return null;
        }

        TestPassForm testPassForm = buildAnswerForm(question);
        return testPassForm;
    }

    public Integer checkCorrectAnswers(TestPassForm form, TestSessionInfo testSessionInfo) {
        Question question = getQuestionByNumber(testSessionInfo.getTestId(), testSessionInfo.getQuestionNumber());

        if (question == null) {
            return 0;
        }

        List<Answer> answers = question.getAnswers();

        return checkCorrectAnswers(answers, form.getAnswer());
    }

    public TestPassForm buildAnswerForm(Question question) {
        TestPassForm testPassForm = new TestPassForm();
        testPassForm.setQuestion(question);
        testPassForm.setAnswers(question.getAnswers());
        testPassForm.setTimePerQuestion(question.getTime());
        return testPassForm;
    }

    @Override
    public TestSessionInfo initTestSessionInfo(Long testId) {

        TestSessionInfo testSessionInfo = new TestSessionInfo();

        testSessionInfo.clear(testId);

        return testSessionInfo;
    }

    @Override
    public int getMaxPageResult(Account account, Integer count) {
        return Calculation.getMaxPage(testResultDao.getMaxPageResult(account), count);
    }

    @Override
    public int getMaxPageTests(Integer count) {
        return Calculation.getMaxPage(testDao.getAllTestsCount(), count);
    }
}
