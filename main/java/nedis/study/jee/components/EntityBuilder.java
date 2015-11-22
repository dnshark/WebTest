package nedis.study.jee.components;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;

/**
 * @author nedis
 * @version 1.0
 */
public interface EntityBuilder {

	Account buildAccount();
	
	AccountRole buildAccountRole(Account account, Role role);
}
