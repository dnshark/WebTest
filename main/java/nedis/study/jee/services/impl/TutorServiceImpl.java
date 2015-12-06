package nedis.study.jee.services.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.TestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.TutorService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class TutorServiceImpl extends CommonServiceImpl implements TutorService {
    @Autowired
    private AccountDao accountDao;

    @Autowired
    private TestDao testDao;

    @Override
    public List<Test> getTestList(Account account) {
        return accountDao.getListTest(account);
    }

    @Override
    @Transactional
    public void createTest(TestForm form) {
       Test test = entityBuilder.buildTest();
       test.setName(form.getName());
       test.setTimePerQuestion(form.getTime());
       Account account =getLoginAccount();
       test.setAccount(account);
       testDao.save(test);
    }
}
