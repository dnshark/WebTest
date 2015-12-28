package nedis.study.jee.dao;

import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;

/**
 * Created by Дмитрий on 24.11.2015.
 */
public interface QuestionDao extends IEntityDao<Question> {

    Question getQuestionByNumber(int number, Test test);
}