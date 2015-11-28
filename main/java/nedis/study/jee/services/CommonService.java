package nedis.study.jee.services;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.List;

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
	
	Account signUp (UserForm form) throws InvalidUserInputException, MessagingException, FileNotFoundException, UnknownHostException;
	
	List<Role> listAllRoles();
}
