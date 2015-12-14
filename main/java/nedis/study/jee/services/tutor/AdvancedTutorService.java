package nedis.study.jee.services.tutor;

import nedis.study.jee.entities.Test;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface AdvancedTutorService {

    List<Test> getAllTests(int offSet,int count);
}
