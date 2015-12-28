package nedis.study.jee.dao;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRegistration;

/**
 * Created by Дмитрий on 27.11.2015.
 */
public interface AccountRegistrationDao extends IEntityDao<AccountRegistration> {

    AccountRegistration getAccountRegistration(Account a);

    AccountRegistration findByHash(final String hash);
}
