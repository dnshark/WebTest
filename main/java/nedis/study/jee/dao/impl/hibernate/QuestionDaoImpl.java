package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 24.11.2015.
 */
@Repository("hibernateQuestionDao")
public class QuestionDaoImpl extends AbstractEntityDao<Question> implements QuestionDao {
    @Override
    protected Class<Question> getEntityClass() {
        return Question.class;
    }

    @Override
    public Question getQuestionByNumber(int number, Test test) {
        return (Question) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("test", test)).setFirstResult(number)
                .setMaxResults(1)
                .addOrder(Order.asc("idQuestion")).uniqueResult();
    }
}
