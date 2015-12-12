package nedis.study.jee.services;

import nedis.study.jee.controllers.AdminControler;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.AdminForm;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdminService {

    List<Account> loadAllUser(int offSet,int count);

    Account getAccount(Long userId);

    void updateUser(Long userId, AdminForm form);

    void deleteUser(Long userId);

    Account addUser(AdminForm form);

    List<String> getRoles(Account user);

    AdminForm getAdminForm(Model model, Account user);
}
