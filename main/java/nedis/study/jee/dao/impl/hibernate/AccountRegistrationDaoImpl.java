package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.AccountRegistrationDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRegistration;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 27.11.2015.
 */
@Repository("hibernateAccountRegistrationDao")
public class AccountRegistrationDaoImpl extends AbstractEntityDao<AccountRegistration> implements AccountRegistrationDao {
    @Override
    protected Class<AccountRegistration> getEntityClass() {
        return AccountRegistration.class;
    }

    @Override
    public AccountRegistration getAccountRegistration(Account a) {
        return (AccountRegistration) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("account", a)).uniqueResult();
    }

    @Override
    public AccountRegistration findByHash(final String hash) {
        return (AccountRegistration) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("hash", hash)).uniqueResult();
    }
}
