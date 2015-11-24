package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.dao.QuestionDao;
import nedis.study.jee.entities.Question;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Дмитрий on 24.11.2015.
 */
@Repository("hibernateQuestionDao")
public class QuestionDaoImpl extends AbstractEntityDao<Question> implements QuestionDao{
    @Override
    protected Class<Question> getEntityClass() {
        return Question.class;
    }
}
