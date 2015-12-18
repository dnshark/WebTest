package nedis.study.jee.dao.impl.hibernate;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;

import nedis.study.jee.entities.Test;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author nedis
 * @version 1.0
 */
@Repository("hibernateAccountDao")
@SuppressWarnings("unchecked")
public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {

	@Override
	public List<Account> listAccounts(final int offset, final int count) {
		return getSession().createCriteria(getEntityClass()).setFirstResult(offset).setMaxResults(count)
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
	public List<Test> getListTest(Account account,int offset, int count) {
		return (List<Test>) getSession().createCriteria(Test.class).add(Restrictions.eq("account", account))
				.setFirstResult(offset)
				.setMaxResults(count)
				.list();
	}

	@Override
	public Long getListCount() {
		return (Long)getSession().createCriteria(getEntityClass())
				.setProjection(Projections.rowCount()).uniqueResult();
	}

	@Override
	public void clearNotConfirmedUsers() {
		Calendar c=Calendar.getInstance();
		c.add(Calendar.DATE,-1);
		List<Account> list=(List<Account>) getSession().createCriteria(getEntityClass())
				.add(Restrictions.and(Restrictions.lt("created", c.getTimeInMillis()), Restrictions.eq("confirmed", false)))
				.list();
		getSession().delete(list);
	}
}
