package nedis.study.jee.components;

import nedis.study.jee.entities.*;

/**
 * @author nedis
 * @version 1.0
 */
public interface EntityBuilder {

	Account buildAccount();
	
	AccountRole buildAccountRole(Account account, Role role);

	TestResult buildTestResult(Account account,Test test);
}
