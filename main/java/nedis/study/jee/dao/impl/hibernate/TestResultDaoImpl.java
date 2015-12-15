package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestResultDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.TestResult;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
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
    public List<TestResult> getUserResults(Account account,int page, int count) {
        return (List<TestResult>) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("account", account))
                .setFirstResult(page)
                .setMaxResults(count)
                .addOrder(Order.desc("created")).list();
    }

    @Override
    public Long getMaxPageResult(Account account) {
        return (Long)getSession().createCriteria(getEntityClass()).add(Restrictions.eq("account", account))
                .setProjection(Projections.rowCount()).uniqueResult();
    }
}
