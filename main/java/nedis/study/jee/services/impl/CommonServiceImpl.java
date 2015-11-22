package nedis.study.jee.services.impl;

import java.util.List;

import nedis.study.jee.components.EntityBuilder;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.AccountRoleDao;
import nedis.study.jee.dao.RoleDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.SignUpForm;
import nedis.study.jee.services.CommonService;
import nedis.study.jee.services.EmailService;
import nedis.study.jee.utils.ReflectionUtils;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nedis
 * @version 1.0
 */
@Service("commonService")
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class CommonServiceImpl implements CommonService {

	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	@Qualifier("hibernateRoleDao")
	private RoleDao roleDao;
	
	@Autowired
	private AccountRoleDao accountRoleDao;
	
	@Autowired
	private EntityBuilder entityBuilder;
	
	@Autowired
	private EmailService emailService;
	
	public CommonServiceImpl() {
		super();
	}

	@Override
	public Account login(String login, String password, int role) throws InvalidUserInputException {
		Account a = accountDao.findByLogin(login);
		if(a == null) {
			throw new InvalidUserInputException("Bad credentials");
		}
		if(!StringUtils.equals(password, a.getPassword())) {
			throw new InvalidUserInputException("Bad credentials");
		}
		if(!a.getActive()) {
			throw new InvalidUserInputException("Account is not active");
		}
		boolean found = false;
		for(AccountRole ar : a.getAccountRoles()) {
			if(ar.getRole().getIdRole().intValue() == role) {
				found= true;
				break;
			}
		}
		if(!found) {
			throw new InvalidUserInputException("Current account does not have selected role");
		}
		return a;
	}
	
	@Override
	@Transactional(readOnly=false, rollbackFor={InvalidUserInputException.class, RuntimeException.class})
	public Account signUp(SignUpForm form) throws InvalidUserInputException {
		Account a = entityBuilder.buildAccount();
		ReflectionUtils.copyByFields(a, form);
		accountDao.save(a);
		
		Role r = roleDao.getStudentRole();
		AccountRole ar = entityBuilder.buildAccountRole(a, r);
		accountRoleDao.save(ar);
		
		emailService.sendVerificationEmail();
		
		return a;
	}

	@Override
	public List<Role> listAllRoles() {
		return roleDao.findAll();
	}
}
