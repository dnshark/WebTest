package nedis.study.jee.dao.impl.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;

/**
 * @author nedis
 * @version 1.0
 */
@Repository("jpaAccountDao")
@SuppressWarnings("unchecked")
public class AccountDaoImpl extends AbstractEntityDao<Account> implements AccountDao {

	@Override
	public List<Account> listAccounts(int offset, int count) {
		return em.createQuery("from Account").setFirstResult(offset).setMaxResults(count).getResultList();
	}

	@Override
	protected Class<Account> getEntityClass() {
		return Account.class;
	}

	@Override
	public Account findByLogin(String login) {
		List<Account> list = em.createQuery("from Account a where a.email = :email").setParameter("login", login).getResultList();
		return list.isEmpty() ? null : list.get(0);
	}
}
