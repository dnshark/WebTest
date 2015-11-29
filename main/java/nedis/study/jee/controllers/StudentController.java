package nedis.study.jee.controllers;

import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class StudentController extends AbstractController {

	@Autowired
	protected StudentService studentService;

	protected void initTests(Model model){
		List<Test> tests = studentService.listAllTests();
		model.addAttribute("tests", tests);
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String showStudent(Model model){
		return "student/home";
	}

	@RequestMapping(value="/tests", method=RequestMethod.GET)
	public String showTest(Model model){
		initTests(model);
		return "student/tests";
	}

	@RequestMapping(value="/question/id{testId}", method=RequestMethod.GET)
	public String showQuestion(Model model,@PathVariable String testId){

		Question question = studentService.getFirstQuestion(testId);

		model.addAttribute("question",question);
		model.addAttribute("answers", studentService.getAnswers(question));
		return "student/question";
	}

	@RequestMapping(value="question/id{questionId}", method=RequestMethod.POST)
	public String GetAnswer(Model model,@PathVariable String questionId) {
		Question question = studentService.getQuestionById(Long.valueOf(questionId));
		question = studentService.getNextQuestion(question);

		if (question == null) {
			return "student/result";
		} else {
			model.addAttribute("question",question);
			model.addAttribute("answers", studentService.getAnswers(question));
			return "student/question";
		}
	}

}
