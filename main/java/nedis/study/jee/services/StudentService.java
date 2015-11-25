package nedis.study.jee.services;

import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;

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
}
