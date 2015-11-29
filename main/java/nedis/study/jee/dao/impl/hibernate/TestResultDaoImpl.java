package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.TestResult;
import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 29.11.2015.
 */
@Repository("hibernateTestResultDao")
public class TestResultDaoImpl extends AbstractEntityDao<TestResult> implements TestResultDao {
    @Override
    protected Class<TestResult> getEntityClass() {
        return TestResult.class;
    }
}
