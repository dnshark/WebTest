package nedis.study.jee.services.allAccess.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.AccountRegistrationDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRegistration;
import nedis.study.jee.services.allAccess.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Дмитрий on 27.11.2015.
 */
@Service("signUpService")
public class SignUpServiceImpl implements SignUpService {
    @Autowired
    AccountRegistrationDao accountRegistrationDao;

    @Autowired
    AccountDao accountDao;

    public Account getAccountByHash(String hash) {
        AccountRegistration accountRegistration = accountRegistrationDao.findByHash(hash);
        if (accountRegistration==null){
            return null;
        }else
            return accountRegistration.getAccount();
    }

    @Override
    @Transactional
    public void confirmAccount(Account account) {
        account.setConfirmed(true);
        account.setActive(true);
        accountDao.update(account);
        AccountRegistration accountRegistration = account.getAccountRegistration();
        accountRegistrationDao.delete(accountRegistration);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountDao.findByEmail(email);
    }
}
