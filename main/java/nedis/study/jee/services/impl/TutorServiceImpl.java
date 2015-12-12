package nedis.study.jee.services.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.AnswerDao;
import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.NewAnswerForm;
import nedis.study.jee.forms.QuestionEditForm;
import nedis.study.jee.forms.TestForm;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.TutorService;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
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
    public List<Test> getTestList(Account account) {
        return accountDao.getListTest(account);
    }

    @Override
    @Transactional
    public Test createTest(TestForm form) {
       Test newTest = entityBuilder.buildTest();
       newTest.setName(form.getName());
       newTest.setTimePerQuestion(Integer.valueOf(form.getTimePerQuestion()));
       newTest.setDescription(form.getDescription());
       Account account =getLoginAccount();
       newTest.setAccount(account);
       testDao.save(newTest);
        return newTest;
    }

    @Override
    public Test getTest(String testId) {
        return testDao.findById(Long.valueOf(testId));
    }

    @Override
    public Question getQuestion(Long questionId) {
        return questionDao.findById(questionId);
    }

    @Override
    @Transactional
    public Question updateQuestion(QuestionEditForm form, Long questionId) {
        Question question = getQuestion(questionId);
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
        int leng;
        if (answersId==null) {leng=0;}
        else
        {leng=answersId.size();} return leng;
    }

    @Override
    @Transactional
    public void deleteQuestion(Long aLong) {
        Question question = questionDao.findById(aLong);
        questionDao.delete(question);
    }

    @Override
    @Transactional
    public void deleteAnswer(Long aLong) {
        Answer answer = answerDao.findById(aLong);
        answerDao.delete(answer);
    }

    @Override
    @Transactional
    public void deleteTest(Long testId) {
        Test test =testDao.findById(testId);
        testDao.delete(test);
    }

    @Override
    @Transactional
    public Answer addAnswer(NewAnswerForm newAnswerForm) {
      Answer answer = entityBuilder.buildAnswer();
      answer.setName(newAnswerForm.getName());
      answer.setCorrect(newAnswerForm.getCorrect());
      Question question = questionDao.findById(newAnswerForm.getQuestionId());
      answer.setQuestion(question);
      answerDao.save(answer);
      return answer;
    }

    @Override
    @Transactional
    public Question addQuestion(QuestionEditForm form) {
        Question question = entityBuilder.buildQuestion();
        Test test = testDao.findById(form.getTestId());
        question.setName(form.getQuestionName());
        question.setTest(test);
        questionDao.save(question);
        return question;
    }

    @Override
    @Transactional
    public Test updateTest(TestForm form) {
        Test test = testDao.findById(form.getIdTest());
        ReflectionUtils.copyByFields(test,form);
        testDao.update(test);
        return test;
    }
}
