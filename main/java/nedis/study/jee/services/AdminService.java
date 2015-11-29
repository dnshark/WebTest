package nedis.study.jee.services;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdminService {

    List<Account> loadAllUser();

    Account getAccount(Long userId);

    void updateUser(Long userId, UserForm form);

    void deleteUser(Long userId);

    Account addUser(UserForm form);
}
