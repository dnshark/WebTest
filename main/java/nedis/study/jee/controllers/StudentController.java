package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.AnswerForm;
import nedis.study.jee.services.StudentService;

import nedis.study.jee.services.impl.utils.TestSessionInfo;
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
	public String showStudent(){
		return "student/home";
	}

	@RequestMapping(value="/tests", method=RequestMethod.GET)
	public String showTest(Model model){
		initTests(model);
		model.addAttribute("mode","online");
		return "student/tests";
	}
	//NEDIS
	@RequestMapping(value="/question/id{testId}", method=RequestMethod.GET)
	public String showQuestion(Model model,HttpSession session,@PathVariable String testId){

		TestSessionInfo testSessionInfo= new TestSessionInfo(session);

		testSessionInfo.clear(Long.valueOf(testId));

		return "redirect:question/next";
	}
	@RequestMapping(value="question/noAnswer")
	public String DoNoAnswer(Model model,HttpSession session,@ModelAttribute("answerForm") AnswerForm form){
		form.setAnswer(null);
		return DoAnswer(model, session, form);
	}
	@RequestMapping(value="question/next", method=RequestMethod.POST)
	public String GetAnswer(Model model,HttpSession session,@ModelAttribute("answerForm") AnswerForm form) {
		return DoAnswer(model, session, form);
	}

	private String doAnswer(Model model, HttpSession session, AnswerForm form) {
		Account account = commonService.getLoginAccount();

		Question question = studentService.doNextQuestion(session,form);

		if (question == null) {
			studentService.saveResult(account,
					(String)session.getAttribute("CURRENT_TEST"),
					(Integer)session.getAttribute("CORRECT_ANSWER")
					);
			return "redirect:/allAccess/result";
		} else {
			model.addAttribute("question",question);
			model.addAttribute("answers", studentService.getAnswers(question));
			model.addAttribute("time", studentService.getTimePerQuestion());
			return "student/question";
		}
	}

	@RequestMapping(value="/offTest", method=RequestMethod.GET)
	public String showOffTest(Model model){
		initTests(model);
		model.addAttribute("mode","offline");
		return "student/tests";
	}

	@RequestMapping(value="/offTest/id{testId}", method=RequestMethod.GET)
	public String showOffTests(Model model,HttpSession session,@PathVariable String testId){
		Test test =studentService.GetTestById(Long.valueOf(testId));
		model.addAttribute("test",test);
		return "student/offTest";
	}
}
