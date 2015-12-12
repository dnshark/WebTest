package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.AnswerForm;
import nedis.study.jee.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	protected void initTests(Model model,int offSet,int count){
		List<Test> tests = studentService.listAllTests(offSet,count);
		model.addAttribute("tests", tests);
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String showStudent(){
		return "student/home";
	}

	@RequestMapping(value="/tests", method=RequestMethod.GET)
	public String showTest(Model model,@RequestParam int offSet,int count){
		initTests(model,offSet,count);
		model.addAttribute("mode","online");
		return "student/tests";
	}

	@RequestMapping(value="/test/start/id{testId}", method=RequestMethod.GET)
	public String showQuestion(Model model,HttpSession session,@PathVariable Long testId){

		session.setAttribute("TEST_INFO", studentService.initTestSessionInfo(testId));

		return "redirect:question/next";
	}

	@RequestMapping(value="question/noAnswer")
	public String doNoAnswer(Model model,HttpSession session,@ModelAttribute("answerForm") AnswerForm form){
		form.setAnswer(null);
		return doAnswer(model, session, form);
	}

	@RequestMapping(value="question/next", method=RequestMethod.POST)
	public String getAnswer(Model model,HttpSession session,@ModelAttribute("answerForm") AnswerForm form) {
		return doAnswer(model, session, form);
	}

	private String doAnswer(Model model, HttpSession session, AnswerForm form) {
		if (form==null) {
			form = new AnswerForm();
		}

		Account account = commonService.getLoginAccount();

		form = studentService.doAnswer(session, form,account);

		if (form==null) {
			return "redirect:/allAccess/result";
		} else {
			model.addAttribute("answerForm", form);
			return "student/question";
		}
	}

	@RequestMapping(value="/offTest", method=RequestMethod.GET)
	public String showOffTest(Model model,@RequestParam int offSet, int count){
		initTests(model,offSet,count);
		model.addAttribute("mode","offline");
		return "student/tests";
	}

	@RequestMapping(value="/offTest/test/id{testId}", method=RequestMethod.GET)
	public String showOffTests(Model model,HttpSession session,@PathVariable Long testId){
		Test test =studentService.getTestById(testId);
		model.addAttribute("test",test);
		return "student/offTest";
	}
}
