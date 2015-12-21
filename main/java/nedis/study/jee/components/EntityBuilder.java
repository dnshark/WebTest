package nedis.study.jee.components;

import nedis.study.jee.entities.*;

/**
 * @author nedis
 * @version 1.0
 */
//Nedis как быстро перейти с EntityBuilder на Impl
public interface EntityBuilder {

    Account buildAccount();

    AccountRole buildAccountRole(Account account, Role role);

    TestResult buildTestResult(Account account, Test test);

    Test buildTest();

    Answer buildAnswer();

    Question buildQuestion();
}
