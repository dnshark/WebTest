package nedis.study.jee.dao.impl.hibernate;

import java.util.List;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;

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
		return getSession().createCriteria(getEntityClass()).setFirstResult(offset).setMaxResults(count).list();
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
}
