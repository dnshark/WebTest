package nedis.study.jee.services.tutor.impl;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.services.tutor.TutorService;
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
    public List<Test> getTestList(Account account,int offSet,int count) {
        return testDao.findAll();
    }

    @Override
    public Boolean checkPermission(Test test, Account account){
        return true;
    }

    @Override
    public  String getHelo(){
       return "Helo advanced tutor";
    }
}
