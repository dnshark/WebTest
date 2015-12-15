package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.TestDao;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String hql = "SELECT COUNT(Answer.correct) FROM Test T"+
                " join T.questions Question"+
                " join Question.answers Answer"+
                " where Answer.correct=true and T.idTest=:test_id";
        Query query = getSession().createQuery(hql).setParameter("test_id",test.getIdTest());
        Long result = (Long) query.uniqueResult();
        return result.intValue();
    }

    @Override
    public List<Question> getListQuestion(Test test, Integer offSet, Integer count) {
        return (List<Question>) getSession().createCriteria(Question.class).add(Restrictions.eq("test", test))
                .setFirstResult(offSet)
                .setMaxResults(count)
                .list();
    }

    @Override
    public List<Test> getTestList(Integer offSet, Integer count) {
        return (List<Test>) getSession().createCriteria(Test.class)
                .setFirstResult(offSet)
                .setMaxResults(count)
                .list();
    }

}
