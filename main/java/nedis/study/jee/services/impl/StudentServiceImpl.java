package nedis.study.jee.services.impl;

import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.*;
import nedis.study.jee.forms.AnswerForm;
import nedis.study.jee.services.impl.utils.TestSessionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.StudentService;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StudentServiceImpl implements StudentService {

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
    public List<Test> listAllTests(int offSet,int count) {
        return testDao.findAll();
    }

    @Override
    public List<TestResult> listAllResult(Account account,int offSet,int count) {
        return testResultDao.getUserResults(account,offSet,count);
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

        if (exists && !answer.getCorrect())
        {
            result = -1;
        }

        return result;
    }

    @Override
    public Integer checkCorrectAnswers(List<Answer> answers, ArrayList<String> userAnswers) {
        Integer correct = 0;
        if (userAnswers==null) {
            return correct;
        }

        for (Answer answer : answers) {
            correct = correct +(checkCorrectAnswer(answer, userAnswers));
        }

        if (correct<0) {
            correct=0;
        }
        return correct;
    }

    @Override
    @Transactional
    public TestResult saveResult(Account current_account, TestSessionInfo testSessionInfo) {
        Test test = testDao.findById(testSessionInfo.getTestId());
        TestResult testResult = entityBuilder.buildTestResult(current_account,test);
        testResult.setCorrectAnswer(testSessionInfo.getCorrectAnswer());
        testResult.setAllCount(testDao.getCorrectCountAnswer(test));
        testResultDao.save(testResult);
        return testResult;
    }

    @Override
    public AnswerForm doAnswer(HttpSession session, AnswerForm form, Account account) {

        TestSessionInfo testSessionInfo = (TestSessionInfo) session.getAttribute("TEST_INFO");

        testSessionInfo.incCorrectAnswer(checkCorrectAnswers(form, testSessionInfo));

        Question question = getQuestionByNumber(testSessionInfo.getTestId(), testSessionInfo.incQuestNumber());

        session.setAttribute("TEST_INFO",testSessionInfo);

        if (question == null) {
            saveResult(account,testSessionInfo);
            return null;
        }

        AnswerForm answerForm = buildAnswerForm(testSessionInfo, question);
        return answerForm;
    }

    public Integer checkCorrectAnswers(AnswerForm form, TestSessionInfo testSessionInfo) {
        Question question = getQuestionByNumber(testSessionInfo.getTestId(), testSessionInfo.getQuestionNumber());

        List<Answer> answers = question.getAnswers();

        return checkCorrectAnswers(answers, form.getAnswer());
    }

    public AnswerForm buildAnswerForm(TestSessionInfo testSessionInfo, Question question) {
        AnswerForm answerForm = new AnswerForm();
        answerForm.setQuestion(question);
        answerForm.setAnswers(question.getAnswers());
        answerForm.setTimePerQuestion(testSessionInfo.getTimePerQuestion());
        return answerForm;
    }

    @Override
    public TestSessionInfo initTestSessionInfo(Long testId) {

        TestSessionInfo testSessionInfo= new TestSessionInfo();

        testSessionInfo.clear(testId);

        Test test = testDao.findById(testId);

        testSessionInfo.setTimePerQuestion(test.getTimePerQuestion());

        return testSessionInfo;
    }
}
