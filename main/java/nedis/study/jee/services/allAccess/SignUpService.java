package nedis.study.jee.services.allAccess;

import nedis.study.jee.entities.Account;

/**
 * Created by Дмитрий on 27.11.2015.
 */
public interface SignUpService {
    public Account getAccountByHash(String hash);

    public void confirmAccount(Account account);

    public Account getAccountByEmail(String email);
}
