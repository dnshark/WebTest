package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Repository("hibernateAccountDao")
@SuppressWarnings("unchecked")
public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {

    @Override
    public List<Account> listAccounts(final int offset, final int count) {
        return getSession().createCriteria(getEntityClass())
                .setFirstResult(offset)
                .setMaxResults(count)
                .list();
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account findByLogin(final String login) {
        return (Account) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("login", login)).uniqueResult();
    }

    @Override
    public Account findByEmail(String email) {
        return (Account) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("email", email)).uniqueResult();
    }

    @Override
    public List<Test> getListTest(Account account, int offset, int count) {
        return (List<Test>) getSession().createCriteria(Test.class).add(Restrictions.eq("account", account))
                .setFirstResult(offset)
                .setMaxResults(count)
                .list();
    }

    @Override
    public Long getListCount() {
        return (Long) getSession().createCriteria(getEntityClass())
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    public void clearNotConfirmedUsers() {
        Timestamp timestamp = getYesterday();

        String hql = "delete from Account where created< :yesterday AND confirmed= :flag";
        Query query = getSession().createQuery(hql);
        query.setTimestamp("yesterday", timestamp);
        query.setBoolean("flag", false);
        query.executeUpdate();
    }

    @Override
    public void delete(Long userId) {
        getSession().createQuery("delete from Account where id=:id").setParameter("id", userId).executeUpdate();
    }

    private Timestamp getYesterday() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1);
        Long l = c.getTimeInMillis();
        return new Timestamp(l);
    }
}
