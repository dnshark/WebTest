package nedis.study.jee.dao;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AccountDao extends IEntityDao<Account> {

    List<Account> listAccounts(int offset, int count);

    Account findByLogin(String login);

    Account findByEmail(String email);

    List<Test> getListTest(Account account, int offset, int count);

    Long getListCount();

    void clearNotConfirmedUsers();

    void delete(Long userId);
}
