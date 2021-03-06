package nedis.study.jee.services.allAccess.impl;

import com.restfb.types.User;
import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.AccountRegistrationDao;
import nedis.study.jee.dao.AccountRoleDao;
import nedis.study.jee.dao.RoleDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRegistration;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.forms.admin.AdminForm;
import nedis.study.jee.security.CurrentAccount;
import nedis.study.jee.security.SecurityUtils;
import nedis.study.jee.services.allAccess.CommonService;
import nedis.study.jee.services.allAccess.TemplateService;
import nedis.study.jee.utils.ReflectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

/**
 * @author nedis
 * @version 1.0
 */
@Service("commonService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonServiceImpl implements CommonService {

    protected final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    private AccountDao accountDao;

    @Autowired
    @Qualifier("hibernateRoleDao")
    private RoleDao roleDao;

    @Autowired
    private AccountRoleDao accountRoleDao;

    @Autowired
    private AccountRegistrationDao accountRegistrationDao;

    @Autowired
    protected EntityBuilder entityBuilder;

    @Autowired
    private TemplateService templateService;

    public CommonServiceImpl() {
        super();
    }

    @Override
    public Account login(String login, String password, int role) throws InvalidUserInputException {
        Account a = accountDao.findByLogin(login);
        if (a == null) {
            LOGGER.info("Can't find login :"+ login);
            throw new InvalidUserInputException("Bad credentials");
        }
        if (!StringUtils.equals(password, a.getPassword())) {
            LOGGER.info("Incorrect password by user:"+ login);
            throw new InvalidUserInputException("Bad credentials");
        }
        if (!a.getActive()) {
            LOGGER.info("try login inactive account:"+ login);
            throw new InvalidUserInputException("Account is not active");
        }
        boolean found = false;
        for (AccountRole ar : a.getAccountRoles()) {
            if (ar.getRole().getIdRole().intValue() == role) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new InvalidUserInputException("Current account does not have selected role");
        }
        return a;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public Account login(User user) throws InvalidUserInputException, FileNotFoundException, MessagingException, UnknownHostException {
        Account a = accountDao.findByLogin(user.getEmail());
        if (a != null) {
            return a;
        } else {
            UserForm form = new UserForm();
            form.setEmail(user.getEmail());
            form.setFio(user.getName());
            form.setLogin(user.getEmail());

            UUID pwd = UUID.randomUUID();//generate temp password
            form.setPassword(pwd.toString());

            return signUp(form, false);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = {InvalidUserInputException.class, RuntimeException.class})
    public Account signUp(UserForm form, boolean sendVerificationEmail) throws InvalidUserInputException, MessagingException, FileNotFoundException {

        Account a = addAccount(form);
        if (sendVerificationEmail) {
            templateService.sendVerificationEmail(form);
        }

        return a;
    }

    @Override
    @Transactional
    public Account addAccount(AdminForm form) {
        Account a = createUser(form);

        initRoles(form.getCheckRoles(), a);

        return a;
    }

    @Override
    @Transactional
    public void initRoles(List<String> checkRoles, Account a) {
        if (a.getAccountRoles() != null) {
            for (AccountRole ar : a.getAccountRoles()) {
                String id = String.valueOf(ar.getRole().getId());
                if (checkRoles.contains(id)) {
                    checkRoles.remove(id);
                } else {
                    accountRoleDao.delete(ar);
                }
            }
        }

        for (String str : checkRoles) {
            Role r = roleDao.getRole(Integer.valueOf(str));
            AccountRole ar = entityBuilder.buildAccountRole(a, r);
            accountRoleDao.save(ar);
        }
    }

    @Override
    @Transactional
    public void updateAccount(Account account) {
        account.setUpdated(new Timestamp(System.currentTimeMillis()));
        accountDao.update(account);
    }

    private void addHashToAccount(Account a, String hash) {
        AccountRegistration aReg = new AccountRegistration();
        aReg.setAccount(a);
        aReg.setHash(hash);
        accountRegistrationDao.save(aReg);
    }

    private void initStudentRole(Account a) {
        Role r = roleDao.getStudentRole();
        AccountRole ar = entityBuilder.buildAccountRole(a, r);
        accountRoleDao.save(ar);
    }

    @Override
    public List<Role> listAllRoles() {
        return roleDao.findAll();
    }

    @Override
    @Transactional
    public Account addAccount(UserForm form) {
        Account a = createUser(form);

        initStudentRole(a);

        return a;
    }

    private Account createUser(UserForm form) {
        Account a = entityBuilder.buildAccount();
        ReflectionUtils.copyByFields(a, form);
        accountDao.save(a);

        String hash = UUID.randomUUID().toString();
        form.setHash(hash);

        addHashToAccount(a, hash);
        return a;
    }

    public Account getLoginAccount() {
        CurrentAccount currentAccount = SecurityUtils.getCurrentAccount();
        return accountDao.findById(currentAccount.getIdAccount());
    }

    public UserForm getUserForm(Account account) {
        UserForm userForm = new UserForm();
        ReflectionUtils.copyByFields(userForm, account);
        return userForm;
    }
    /*
	@Override
	@Transactional(readOnly=false, rollbackFor={InvalidUserInputException.class, RuntimeException.class})
	public Account login(User user) throws InvalidUserInputException {
		Account a = accountDao.findByLogin(user.getEmail());
		if(a != null) {
			return a;
		}
		else{
			SignUpForm form = new SignUpForm ();
			form.setEmail(user.getEmail());
			form.setSurname(user.getLastName());
			form.setName(user.getFirstName());
			form.setSecondName(user.getMiddleName());
			form.setLogin(user.getEmail());

			UUID pwd = UUID.randomUUID();//generate temp password
			form.setPassword(pwd.toString());
			form.setPassword2(pwd.toString());

			return signUp(form, false, true);
		}
	}*/
}
