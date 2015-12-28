package nedis.study.jee.services.tutor.impl;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.services.tutor.TutorService;
import nedis.study.jee.utils.Calculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service("advancedTutorService")
public class AdvancedTutorServiceImpl extends TutorServiceImpl implements TutorService {
    @Autowired
    TestDao testDao;

    @Override
    public List<Test> getTestList(Account account, int page, int count) {
        return testDao.getTestList((page - 1) * count, count);
    }

    @Override
    public Boolean checkPermission(Test test, Account account) {
        return true;
    }

    @Override
    public int getTestMaxPageCount(Account account, Integer count) {
        return Calculation.getMaxPage(testDao.getAllTestsCount(), count);
    }

    @Override
    public String getHello() {
        return "Hello advanced tutor";
    }

}
