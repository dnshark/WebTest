package nedis.study.jee.dao;

import java.util.List;

import nedis.study.jee.entities.Account;

/**
 * @author nedis
 * @version 1.0
 */
public interface AccountDao extends IEntityDao<Account> {

	List<Account> listAccounts (int offset, int count);
	
	Account findByLogin(String login);

	Account findByEmail(String email);
}
