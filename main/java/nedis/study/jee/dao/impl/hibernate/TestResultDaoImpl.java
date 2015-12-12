package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.TestResult;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Дмитрий on 29.11.2015.
 */
@Repository("hibernateTestResultDao")
public class TestResultDaoImpl extends AbstractEntityDao<TestResult> implements TestResultDao {
    @Override
    protected Class<TestResult> getEntityClass() {
        return TestResult.class;
    }

    @Override
    public List<TestResult> getUserResults(Account account,int offset, int count) {
        return (List<TestResult>) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("account", account))
                .addOrder(Order.desc("created")).list();
    }
}
