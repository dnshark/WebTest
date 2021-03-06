package nedis.study.jee.services.allAccess;

import com.restfb.types.User;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.forms.admin.AdminForm;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface CommonService {

    Account login(String email, String password, int role) throws InvalidUserInputException;

    Account login(User user) throws InvalidUserInputException, FileNotFoundException, MessagingException, UnknownHostException;

    Account signUp(UserForm form, boolean sendVerificationEmail) throws InvalidUserInputException, MessagingException, FileNotFoundException;

    List<Role> listAllRoles();

    Account addAccount(UserForm form);

    Account addAccount(AdminForm form);

    void initRoles(List<String> checkRoles, Account a);

    void updateAccount(Account account);

    Account getLoginAccount();

    public UserForm getUserForm(Account account);
}
