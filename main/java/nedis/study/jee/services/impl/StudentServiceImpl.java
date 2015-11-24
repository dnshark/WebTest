package nedis.study.jee.services.impl;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import nedis.study.jee.services.StudentService;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class StudentServiceImpl implements StudentService {

    @Autowired
    @Qualifier("hibernateTestDao")
    private TestDao testDao;

    @Override
    public List<Test> listAllTests() {
        return testDao.findAll();
    }

    public Test GetTestById(long testId) {
        return testDao.findById(testId);
    }
}
