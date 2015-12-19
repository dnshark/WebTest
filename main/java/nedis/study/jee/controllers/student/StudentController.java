package nedis.study.jee.controllers.student;

import nedis.study.jee.ApplicationConstants;
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

	protected void initTests(Model model,int page,int count){
		List<Test> tests = studentService.listAllTests(page,count);
		model.addAttribute("tests", tests);
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String showStudent(){
		return "student/home";
	}

	@RequestMapping(value="/tests", method=RequestMethod.GET)
	public String showTest(@RequestParam(value = "page", required = false) Integer page,
						   @RequestParam(value = "count", required = false) Integer count,
						   Model model){

		Account account = commonService.getLoginAccount();

		if (page == null) {page= 1;}
		if (count == null) {count= ApplicationConstants.DEFAULT_PAGE_COUNT;}
		initTests(model, page,count);
		model.addAttribute("mode","online");
		model.addAttribute("maxPages",studentService.getMaxPageTests(count));
		model.addAttribute("page", page);
		return "student/tests";
	}

	@RequestMapping(value="/test/start/id{testId}", method=RequestMethod.GET)
	public String showQuestion(Model model,HttpSession session,@PathVariable Long testId){
//NEDIS
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
			return "redirect:/result";
		} else {
			model.addAttribute("testPassForm", form);
			return "student/question";
		}
	}

	@RequestMapping(value="/offTest", method=RequestMethod.GET)
	public String showOffTest(Model model,@RequestParam int page, int count){
		initTests(model, page,count);
		model.addAttribute("mode","offline");
		return "student/tests";
	}

	@RequestMapping(value="/offTest/id{testId}", method=RequestMethod.GET)
	public String showOffTests(Model model,HttpSession session,@PathVariable Long testId){
		Test test =studentService.getTestById(testId);
		model.addAttribute("test",test);
		return "student/offTest";
	}
}
