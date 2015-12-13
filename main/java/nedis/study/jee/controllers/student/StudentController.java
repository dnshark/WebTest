package nedis.study.jee.controllers.student;

import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.student.TestPassForm;
import nedis.study.jee.services.student.StudentService;

import nedis.study.jee.services.student.TestSessionInfo;
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

		return "redirect:/question/next";
	}

	@RequestMapping(value="question/noAnswer")
	public String doNoAnswer(Model model,HttpSession session,@ModelAttribute("answerForm") TestPassForm form){
		form.setAnswer(null);
		return doAnswer(model, session, form);
	}

	@RequestMapping(value="/question/next", method=RequestMethod.GET)
	public String getAnswer(Model model,HttpSession session) {
		TestSessionInfo testSessionInfo = (TestSessionInfo)session.getAttribute("TEST_INFO");
		Account account = commonService.getLoginAccount();
		TestPassForm form = studentService.getTestPassForm(account,testSessionInfo);
		model.addAttribute("testPassForm",form);
		return "student/question";
	}

	@RequestMapping(value="question/next", method=RequestMethod.POST)
	public String passQuestion(Model model,HttpSession session,@ModelAttribute("answerForm") TestPassForm form) {
		return doAnswer(model, session, form);
	}

	private String doAnswer(Model model, HttpSession session, TestPassForm form) {

		Account account = commonService.getLoginAccount();

		form = studentService.doAnswer(session, form,account);

		if (form==null) {
			return "redirect:/allAccess/result?offSet=0&count=50";
		} else {
			model.addAttribute("testPassForm", form);
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
