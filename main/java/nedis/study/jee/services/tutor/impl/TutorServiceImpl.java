package nedis.study.jee.services.tutor.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.AnswerDao;
import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.exceptions.InvalidUserAccessException;
import nedis.study.jee.forms.tutor.NewAnswerForm;
import nedis.study.jee.forms.tutor.QuestionEditForm;
import nedis.study.jee.forms.tutor.TestForm;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.services.allAccess.impl.CommonServiceImpl;
import nedis.study.jee.services.tutor.TutorService;
import nedis.study.jee.utils.Calculation;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service("tutorService")
public class TutorServiceImpl extends CommonServiceImpl implements TutorService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TestDao testDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private AnswerDao answerDao;

    @Override
    public List<Test> getTestList(Account account, int page, int count) {
        return accountDao.getListTest(account, (page - 1) * count, count);
    }

    @Override
    @Transactional
    public Test createTest(TestForm form) {
        Test newTest = entityBuilder.buildTest();
        newTest.setName(form.getName());
        newTest.setTimePerQuestion(form.getTimePerQuestion());
        newTest.setDescription(form.getDescription());
        Account account = getLoginAccount();
        newTest.setAccount(account);
        testDao.save(newTest);
        return newTest;
    }

    @Override
    public Test getTest(Long testId) {
        return testDao.findById(testId);
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionDao.findById(questionId);
    }

    @Override
    @Transactional
    public Question updateQuestion(QuestionEditForm form, Account account) throws InvalidUserAccessException {
        Question question = getQuestion(form.getQuestionId());

        checkPermission(question.getTest(), account);

        question.setName(form.getQuestionName());

        ArrayList<String> answersId = form.getAnswerId();

        int leng = getLength(answersId);

        for (int i = 0; i < leng; i++) {
            Answer answer = answerDao.findById(Long.valueOf(answersId.get(i)));

            answer.setName(form.getAnswerName().get(i));

            answer.setCorrect(form.getCbItemList().contains(answersId.get(i)));

            answerDao.update(answer);
        }
        return question;
    }

    public int getLength(ArrayList<String> answersId) {

        if (answersId == null) {
            return  0;
        } else {
            return answersId.size();
        }
    }

    @Override
    @Transactional
    public Test deleteQuestion(Long questionId, Account account) throws InvalidUserAccessException {
        Question question = questionDao.findById(questionId);
        Test test = question.getTest();
        checkPermission(test, account);

        questionDao.delete(question);
        return test;
    }

    @Override
    @Transactional
    public void deleteAnswer(Long answerId, Account account) throws InvalidUserAccessException {
        Answer answer = answerDao.findById(answerId);
        checkPermission(answer.getQuestion().getTest(), account);
        answerDao.delete(answer);
    }

    @Override
    @Transactional
    public void deleteTest(Long testId, Account account) throws InvalidUserAccessException {
        Test test = testDao.findById(testId);

        checkPermission(test, account);

        testDao.delete(test);
    }

    @Override
    @Transactional
    public Answer addAnswer(NewAnswerForm newAnswerForm, Account account) throws InvalidUserAccessException {
        Answer answer = entityBuilder.buildAnswer();
        answer.setName(newAnswerForm.getName());
        answer.setCorrect(newAnswerForm.getCorrect());
        Question question = questionDao.findById(newAnswerForm.getQuestionId());
        answer.setQuestion(question);

        checkPermission(question.getTest(), account);

        answerDao.save(answer);
        return answer;
    }

    @Override
    @Transactional
    public Question addQuestion(QuestionEditForm form, Account account) throws InvalidUserAccessException {
        Question question = entityBuilder.buildQuestion();
        Test test = testDao.findById(form.getTestId());
        question.setName(form.getQuestionName());
        checkPermission(test, account);
        question.setTest(test);
        questionDao.save(question);
        return question;
    }

    @Override
    @Transactional
    public Test updateTest(TestForm form, Account account) throws InvalidUserAccessException {
        Test test = testDao.findById(form.getIdTest());
        checkPermission(test, account);
        ReflectionUtils.copyByFields(test, form);
        testDao.update(test);
        return test;
    }

    @Override
    public QuestionEditForm getQuestionEditForm(Long testId) {
        QuestionEditForm questionEditForm = new QuestionEditForm();
        questionEditForm.setTestId(testId);
        return questionEditForm;
    }

    @Override
    public QuestionEditForm fillQuestionEditForm(Question question) {
        QuestionEditForm questionEditForm = new QuestionEditForm();
        questionEditForm.setQuestionId(question.getIdQuestion());
        questionEditForm.setQuestionName(question.getName());
        questionEditForm.setTestId(question.getTest().getIdTest());
        return questionEditForm;
    }

    @Override
    public Boolean checkPermission(Test test, Account account) throws InvalidUserAccessException {
        if (test.getAccount().getIdAccount().equals(account.getIdAccount()))
            return true;
        throw new InvalidUserAccessException("No access");
    }

    @Override
    public String getHello() {
        return "Hello tutor";
    }

    @Override
    public TestForm getTestForm(Long testId, Integer page, Integer count) {
        Test test = getTest(testId);
        TestForm testForm = new TestForm();
        ReflectionUtils.copyByNotNullFields(testForm, test);
        List<Question> list = testDao.getListQuestion(test, (page - 1) * count, count);
        ArrayList<StringId> testQuestions = new ArrayList<StringId>();
        for (Question question : list) {
            testQuestions.add(new StringId(question.getIdQuestion(), question.getName()));
        }
        testForm.setTestQuestions(testQuestions);
        return testForm;
    }

    @Override
    public int getQuestionMaxPageCount(Long testId, Integer count) {
        return Calculation.getMaxPage(testDao.getQuestionCount(testId), count);
    }

    @Override
    public int getTestMaxPageCount(Account account, Integer count) {
        return Calculation.getMaxPage(testDao.getAccountCountTests(account), count);
    }

    @Override
    public List<StringId> getTests(Integer page, Integer count, Account account) {
        List<Test> list = getTestList(account, page, count);
        List<StringId> tests = new ArrayList<StringId>();
        for (Test test : list) {
            tests.add(new StringId(test.getIdTest(), test.getName()));
        }
        return tests;
    }
}
