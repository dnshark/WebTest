package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.TestForm;
import nedis.study.jee.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
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
	public String showQuestion(Model model,HttpSession session,@PathVariable String testId){

		Question question = studentService.getQuestionByNumber(testId,0);

		model.addAttribute("question",question);
		model.addAttribute("answers", studentService.getAnswers(question));
		session.setAttribute("CORRECT_ANSWER", 0);
		session.setAttribute("QUESTION_NUMBER", 0);
		session.setAttribute("CURRENT_TEST",testId);
		model.addAttribute("testForm", new TestForm());
		return "student/question";
	}

	@RequestMapping(value="question/next", method=RequestMethod.POST)
	public String GetAnswer(Model model,HttpSession session,@ModelAttribute("testForm") TestForm form) {
		Integer number = (Integer)session.getAttribute("QUESTION_NUMBER");
		String testId = (String)session.getAttribute("CURRENT_TEST");

		Question question = studentService.getQuestionByNumber(testId, number);

		List<Answer> answers = question.getAnswers();
        Integer correct = (Integer) session.getAttribute("CORRECT_ANSWER");

		correct=correct+studentService.CheckCorrectAnswers(answers,form.getAnswer());
		session.setAttribute("CORRECT_ANSWER",correct);

		question = studentService.getQuestionByNumber(testId,++number);

		Account account = (Account)session.getAttribute("CURRENT_ACCOUNT");

		if (question == null) {
			session.setAttribute("QUESTION_NUMBER", 0);
			studentService.saveResult(account,
					(String)session.getAttribute("CURRENT_TEST"),
					(Integer)session.getAttribute("CORRECT_ANSWER")
					);
			return "redirect:../allAccess/result";
		} else {
			session.setAttribute("QUESTION_NUMBER", number);
			model.addAttribute("question",question);
			model.addAttribute("answers", studentService.getAnswers(question));
			return "student/question";
		}
	}
	@RequestMapping(value="/offTests", method=RequestMethod.GET)
	public String showOffTest(Model model){
		initTests(model);
		return "student/offTests";
	}

	@RequestMapping(value="/offTests/id{testId}", method=RequestMethod.GET)
	public String showOffTests(Model model,HttpSession session,@PathVariable String testId){
		return "student/offTests";
	}
}
