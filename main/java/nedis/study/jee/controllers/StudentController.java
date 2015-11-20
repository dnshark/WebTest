package nedis.study.jee.controllers;

import nedis.study.jee.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class StudentController extends AbstractController {

	@Autowired
	protected StudentService studentService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "student/home";
	}
}
