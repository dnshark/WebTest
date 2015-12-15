package nedis.study.jee.services.tutor.impl;

import nedis.study.jee.controllers.tutor.TutorController;
import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.services.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<Test> getTestList(Account account,int page,int count) {
        return testDao.getTestList((page - 1) * count, count);
    }

    @Override
    public Boolean checkPermission(Test test, Account account){
        return true;
    }

    @Override
    public int getTestMaxPageCount(Account account, Integer count) {
        double d = (double)testDao.getAllTestsCount();
        return  (int)Math.ceil(d / count);
    }

    @Override
    public  String getHelo(){
       return "Helo advanced tutor";
    }

}
