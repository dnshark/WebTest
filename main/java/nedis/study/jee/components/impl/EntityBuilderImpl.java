package nedis.study.jee.components.impl;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;

/**
 * @author nedis
 * @version 1.0
 */
@Component("entityBuilder")
public class EntityBuilderImpl implements EntityBuilder {
	
	@Override
	public Account buildAccount() {
		Account a = new Account();
		a.setCreated(new Timestamp(System.currentTimeMillis()));
		a.setActive(Boolean.FALSE);
		return a;
	}
	
	@Override
	public AccountRole buildAccountRole(Account account, Role role) {
		return new AccountRole(account, role);
	}
}
