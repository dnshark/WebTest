package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Test;
import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 20.11.2015.
 */
@Repository("hibernateTestDao")
public class TestDaoImpl extends AbstractEntityDao<Test> implements TestDao {
    @Override
    protected Class<Test> getEntityClass() {
        return Test.class;
    }

    @Override
    public int getCorrectCountAnswer(Test test) {
        return 100;
    }
}
