package nedis.study.jee.services;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
public interface TutorService {

    List<Test> getTestList(Account account);

}
