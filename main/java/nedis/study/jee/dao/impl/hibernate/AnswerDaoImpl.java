package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.AnswerDao;
import nedis.study.jee.entities.Answer;
import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 11.12.2015.
 */
@Repository("hibernateAnswerDao")
public class AnswerDaoImpl extends AbstractEntityDao<Answer> implements AnswerDao {
    @Override
    protected Class<Answer> getEntityClass() {
        return Answer.class;
    }
}
