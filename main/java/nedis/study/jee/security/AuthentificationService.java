package nedis.study.jee.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * @author nedis
 * @version 1.0
 */
@Service("accountAuthentificationService")
public class AuthentificationService implements UserDetailsService {
	private static final Map<Integer, String> ROLES = new HashMap<Integer, String>();
	static {
		ROLES.put(ApplicationConstants.ADMIN_ROLE, "ADMIN");
		ROLES.put(ApplicationConstants.TUTOR_ROLE, "TUTOR");
		ROLES.put(ApplicationConstants.ADVANCED_TUTOR_ROLE, "ADVANCED_TUTOR");
		ROLES.put(ApplicationConstants.STUDENT_ROLE, "STUDENT");
	}
	static Collection<? extends GrantedAuthority> convert(List<AccountRole> roles) {
		Collection<SimpleGrantedAuthority> res = new ArrayList<SimpleGrantedAuthority>();
		for(AccountRole ar : roles) {
			res.add(new SimpleGrantedAuthority(ROLES.get(ar.getRole().getIdRole().intValue())));
		}
		return res;
	}
	
	@Autowired
	private AccountDao accountDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account a = accountDao.findByLogin(username);
		if(a == null) {
			throw new UsernameNotFoundException("Account not found by id="+username);
		}
		return new CurrentAccount(a);
	}
}
