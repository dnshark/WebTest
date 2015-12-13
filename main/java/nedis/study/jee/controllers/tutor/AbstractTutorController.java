package nedis.study.jee.controllers.tutor;

import nedis.study.jee.controllers.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;

import nedis.study.jee.services.tutor.AdvancedTutorService;
import nedis.study.jee.services.tutor.TutorService;

/**
 * @author nedis
 * @version 1.0
 */
public abstract class AbstractTutorController extends AbstractController {

	@Autowired
	protected TutorService tutorService;
	
	@Autowired
	protected AdvancedTutorService advancedTutorService;
}
