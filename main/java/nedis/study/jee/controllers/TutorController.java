package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.CBItem;
import nedis.study.jee.forms.QuestionEditForm;
import nedis.study.jee.forms.TestForm;
import nedis.study.jee.services.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

	@RequestMapping(value="editTest/id{testId}", method=RequestMethod.GET)
	public String showTestForEdit(Model model,@PathVariable String testId){
		Test test = tutorService.getTest(testId);
		model.addAttribute("test", test);
		return "tutor/editTest";
	}

	@RequestMapping(value="editTest/test{test}")
	public String editTest(Model model,@PathVariable Test test
	){

		TestForm testForm = new TestForm();
		testForm.setTest(test);
		return "tutor/editTest";
	}


	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model){
		Account account = commonService.getLoginAccount();
		model.addAttribute("tests", tutorService.getTestList(account));
		return "tutor/test";
	}
	@RequestMapping(value="/newTest", method=RequestMethod.GET)
	public String showNewTest(Model model){
		TestForm testForm = new TestForm();
		model.addAttribute("testForm", testForm);
		return "tutor/newTest";
	}
	@RequestMapping(value="/addTest")
	public String addNewTest(@ModelAttribute("testForm") TestForm testform){
		tutorService.createTest(testform.getTest());
		return "redirect:/tutor/test";
	}
	@RequestMapping(value="/editQuestion/id{questionId}", method=RequestMethod.GET)
	public String showQuestion(Model model,@PathVariable String questionId){
		QuestionEditForm questionEditForm = new QuestionEditForm();
		Question question = tutorService.getQuestion(questionId);

		questionEditForm.setQuestionId(question.getIdQuestion());
		questionEditForm.setQuestionName(question.getName());

		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("questionEditForm", questionEditForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="/editQuestion/Ok")
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {
		model.addAttribute("question", "1");
		return "tutor/editQuestion";
	}
}
