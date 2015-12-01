package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Дмитрий on 20.11.2015.
 */
@Repository("hibernateTestDao")
public class TestDaoImpl extends AbstractEntityDao<Test> implements TestDao {
    @Override
    protected Class<Test> getEntityClass() {
        return Test.class;
    }

    @Override
    public Integer getCorrectCountAnswer(Test test) {
        List<Question> questions = test.getQuestions();
        Integer correctCount = 0;
        for (Question q : questions) {
           List<Answer> answers = q.getAnswers();

            correctCount += getCountCorrectAnswers(answers);
        }
        return correctCount;
    }

    private Integer getCountCorrectAnswers(List<Answer> answers) {
        Integer correctCount=0;
        for (Answer a : answers) {
            if (a.getCorrect()) {
                correctCount++;
            }
        }
        return correctCount;
    }
}
