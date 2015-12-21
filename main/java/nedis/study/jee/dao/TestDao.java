package nedis.study.jee.dao;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;

import java.util.List;

/**
 * Created by Дмитрий on 20.11.2015.
 */
public interface TestDao extends IEntityDao<Test> {
    int getCorrectCountAnswer(Test test);

    List<Question> getListQuestion(Test test, Integer offset, Integer count);

    List<Test> getTestList(Integer offset, Integer count);

    Long getQuestionCount(Long testId);

    Long getAccountCountTests(Account account);

    Long getAllTestsCount();
}
