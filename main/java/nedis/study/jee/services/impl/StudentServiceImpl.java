package nedis.study.jee.services.impl;

import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.StudentService;
import org.springframework.transaction.annotation.Transactional;

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
    @Qualifier("entityBuilder")
    private EntityBuilder entityBuilder;

    @Override
    public List<Test> listAllTests() {
        return testDao.findAll();
    }

    public Test GetTestById(long testId) {
        return testDao.findById(testId);
    }

    @Override
    public Question getFirstQuestion(String testId) {
        List<Question> questions = GetTestById(Long.valueOf(testId)).getQuestions();

        if (questions.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }

        return questions.get(0);
    }

    @Override
    public List<Answer> getAnswers(Question question) {
        List<Answer> answers = question.getAnswers();

        if (answers.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        return answers;
    }

    @Override
    public Question getQuestionById(long questionId) {
        return questionDao.findById(questionId);
    }

    @Override
    public Question getNextQuestion(Question question) {
        Test test = question.getTest();
        List<Question> listQ = test.getQuestions();

        for(Question q : listQ){

         if (q.getIdQuestion()>question.getIdQuestion()) {
             return q;
         }
        }
        return null;
    }

    @Override
    public boolean CheckCorrectAnswer(Answer answer, List<String> userAnswers) {
        Boolean exists = userAnswers.indexOf(answer.getIdAnswer())>=0;

        return exists && answer.getCorrect();
    }

    @Override
    public Integer CheckCorrectAnswers(List<Answer> answers, List<String> userAnswers) {
        Integer correct = 0;
        for (Answer answer : answers) {
            if (CheckCorrectAnswer(answer, userAnswers)) correct++;
            else{
                correct--;
            }

        }

        if (correct<0) {
            correct=0;
        }
        return correct;
    }

    @Override
    @Transactional
    public TestResult saveResult(Account current_account, String current_test, int correct_answer) {
        Test test = testDao.findById(Long.valueOf(current_test));
        TestResult testResult = entityBuilder.buildTestResult(current_account,test);
        testResult.setCorrectAnswer(correct_answer);
        testResult.setAllCount(testDao.getCorrectCountAnswer(test));
        testResultDao.save(testResult);
        return testResult;
    }
}
