package nedis.study.jee.dao;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.TestResult;

import java.util.List;

/**
 * Created by Дмитрий on 29.11.2015.
 */
public interface TestResultDao extends IEntityDao<TestResult> {
    List<TestResult> getUserResults(Account account,int page, int count);

    Long getMaxPageResult(Account account);
}
