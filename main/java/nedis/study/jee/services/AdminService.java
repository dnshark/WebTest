package nedis.study.jee.services;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.AdminForm;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdminService {

    List<Account> loadAllUser();

    Account getAccount(Long userId);

    void updateUser(Long userId, AdminForm form);

    void deleteUser(Long userId);

    Account addUser(AdminForm form);

    List<Role> getRoles(Account user);
}
