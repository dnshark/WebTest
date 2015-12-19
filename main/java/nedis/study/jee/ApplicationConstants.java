package nedis.study.jee;

import java.util.Arrays;
import java.util.List;


/**
 * @author nedis
 * @version 1.0
 */
public interface ApplicationConstants {
	int ADMIN_ROLE 			= 1;
	int ADVANCED_TUTOR_ROLE = 2;
	int TUTOR_ROLE 			= 3;
	int STUDENT_ROLE 		= 4;
	List<Integer> ROLES = Arrays.asList(new Integer[]{ADMIN_ROLE, ADVANCED_TUTOR_ROLE, TUTOR_ROLE, STUDENT_ROLE});

	String DEFAULT_PAGE_COUNT ="3";
	String SESSION_TEST_INFO = "TEST_INFO";
}
