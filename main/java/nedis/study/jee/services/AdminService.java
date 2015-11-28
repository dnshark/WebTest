package nedis.study.jee.services;

import nedis.study.jee.entities.Account;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdminService {

    List<Account> loadAllUser();

    Account getAccount(Long userId);

}
