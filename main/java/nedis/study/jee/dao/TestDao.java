package nedis.study.jee.dao;

import nedis.study.jee.entities.Test;

/**
 * Created by Дмитрий on 20.11.2015.
 */
public interface TestDao extends IEntityDao<Test> {
    Integer getCorrectCountAnswer(Test test);
}
