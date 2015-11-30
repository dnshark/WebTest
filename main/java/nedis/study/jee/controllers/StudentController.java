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

		Question question = studentService.getFirstQuestion(testId);

		model.addAttribute("question",question);
		model.addAttribute("answers", studentService.getAnswers(question));
		session.setAttribute("CORRECT_ANSWER", 0);
		session.setAttribute("CURRENT_TEST",testId);
		model.addAttribute("testForm", new TestForm());
		return "student/question";
	}

	@RequestMapping(value="student/result", method=RequestMethod.GET)
	public String showResults(Model model,HttpSession session){
		Account account = (Account)session.getAttribute("CURRENT_ACCOUNT");
		studentService.listAllResult(account);
		return "student/result";
	}

	@RequestMapping(value="question/id{questionId}", method=RequestMethod.POST)
	public String GetAnswer(Model model,HttpSession session,@PathVariable String questionId,@ModelAttribute("testForm") TestForm form) {
		Question question = studentService.getQuestionById(Long.valueOf(questionId));

		List<Answer> answers = question.getAnswers();
        Integer correct = (Integer) session.getAttribute("CORRECT_ANSWER");

		correct=correct+studentService.CheckCorrectAnswers(answers,form.getAnswer());
		session.setAttribute("CORRECT_ANSWER",correct);

		question = studentService.getNextQuestion(question);

		Account account = (Account)session.getAttribute("CURRENT_ACCOUNT");

		if (question == null) {
			studentService.saveResult(account,
					(String)session.getAttribute("CURRENT_TEST"),
					(Integer)session.getAttribute("CORRECT_ANSWER")
					);
			return "student/result/id"+String.valueOf(account.getIdAccount());
		} else {
			model.addAttribute("question",question);
			model.addAttribute("answers", studentService.getAnswers(question));
			return "student/question";
		}
	}

}
