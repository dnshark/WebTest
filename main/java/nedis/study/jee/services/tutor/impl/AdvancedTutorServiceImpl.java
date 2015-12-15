package nedis.study.jee.services.tutor.impl;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.tutor.AdvancedTutorService;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class AdvancedTutorServiceImpl implements AdvancedTutorService {
    @Autowired
    TestDao testDao;

    @Override
    public List<Test> getAllTests(int offSet, int count) {
        return testDao.getTestList(offSet,count);
    }
}
