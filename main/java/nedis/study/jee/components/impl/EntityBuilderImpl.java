package nedis.study.jee.components.impl;

import java.sql.Timestamp;

import nedis.study.jee.entities.*;
import org.springframework.stereotype.Component;

import nedis.study.jee.components.EntityBuilder;

/**
 * @author nedis
 * @version 1.0
 */
@Component("entityBuilder")
public class EntityBuilderImpl implements EntityBuilder {

	@Override
	public Account buildAccount() {
		Account a = new Account();
		a.setCreated(new Timestamp(System.currentTimeMillis()));
		a.setActive(Boolean.FALSE);
		a.setConfirmed(Boolean.FALSE);
		return a;
	}

	@Override
	public AccountRole buildAccountRole(Account account, Role role) {
		return new AccountRole(account, role);
	}

	@Override
	public TestResult buildTestResult(Account account, Test test) {
		TestResult testResult = new TestResult();
		testResult.setAccount(account);
		testResult.setTest(test);
		testResult.setCreated(new Timestamp(System.currentTimeMillis()));
		testResult.setTestName(test.getName());
		return testResult;
	}

	@Override
	public Test buildTest() {
		Test test = new Test();
		test.setCreated(new Timestamp(System.currentTimeMillis()));
		test.setActive(Boolean.TRUE);
		return test;
	}

	@Override
	public Answer buildAnswer() {
		Answer answer = new Answer();
		answer.setCreated(new Timestamp(System.currentTimeMillis()));
		answer.setActive(Boolean.TRUE);
		return answer;
	}

	@Override
	public Question buildQuestion() {
		Question question = new Question();
		question.setCreated(new Timestamp(System.currentTimeMillis()));
		question.setActive(Boolean.TRUE);
		return question;
	}
}