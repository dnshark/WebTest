package nedis.study.jee.services.impl;

import nedis.study.jee.dao.AccountDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.TutorService;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class TutorServiceImpl implements TutorService {
    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Test> getTestList(Account account) {
        return accountDao.getListTest(account);
    }
}
