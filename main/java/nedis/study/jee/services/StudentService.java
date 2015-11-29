package nedis.study.jee.services;

import nedis.study.jee.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface StudentService {

    List<Test> listAllTests();

    Test GetTestById(long testId);

    Question getFirstQuestion(String testId);

    List<Answer> getAnswers(Question question);

    Question getQuestionById(long questionId);

    Question getNextQuestion(Question question);

    Integer CheckCorrectAnswer(Answer answer, ArrayList<String> userAnswers);

    Integer CheckCorrectAnswers(List<Answer> answers,ArrayList<String> userAnswers);

    TestResult saveResult(Account current_account, String current_test, int correct_answer);
}
