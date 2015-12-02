package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.QuestionForm;
import nedis.study.jee.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
@RequestMapping("/tutor")
public class TutorController extends AbstractTutorController {

	@Autowired
	private TutorService tutorService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "tutor/home";
	}

	@RequestMapping(value="questionNew")
	public String showQuestion(Model model){
		QuestionForm questionForm = new QuestionForm();
		model.addAttribute("mode","new");  //NEDIS спросить как лучше сделать отображение кнопок
		model.addAttribute("questionForm", questionForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="test/new", method=RequestMethod.GET)
	public String showTest(Model model){
		QuestionForm questionForm = new QuestionForm();
		model.addAttribute("mode","new");  //NEDIS спросить как лучше сделать отображение кнопок
		model.addAttribute("questionForm", questionForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model,HttpSession session){
		Account account = (Account)session.getAttribute("CURRENT_ACCOUNT");
		model.addAttribute("tests", tutorService.getTestList(account));
		return "tutor/test";
	}
}
