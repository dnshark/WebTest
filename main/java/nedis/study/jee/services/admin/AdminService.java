package nedis.study.jee.services.admin;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.admin.AdminForm;
import org.springframework.ui.Model;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdminService {

    List<Account> loadAllUser(int page, int count);

    Account getAccount(Long userId);

    void updateUser(Long userId, AdminForm form);

    void deleteUser(Long userId);

    Account addUser(AdminForm form);

    List<String> getRoles(Account user);

    AdminForm getAdminForm(Model model, Account user);

    Account buildAccount();

    int getUsersMaxPageList(Integer count);
}
