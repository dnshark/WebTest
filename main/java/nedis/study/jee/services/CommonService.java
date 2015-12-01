package nedis.study.jee.services;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.List;

import com.restfb.types.User;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.UserForm;

import javax.mail.MessagingException;

/**
 * @author nedis
 * @version 1.0
 */
public interface CommonService {

	Account login (String email, String password, int role) throws InvalidUserInputException;

	Account login (User user) throws InvalidUserInputException, FileNotFoundException, MessagingException, UnknownHostException;
	
	Account signUp (UserForm form, boolean sendVerificationEmail) throws InvalidUserInputException, MessagingException, FileNotFoundException;
	
	List<Role> listAllRoles();

	Account addAccount(UserForm form);

	void updateAccount(Account account);
}
