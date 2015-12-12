package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.NewAnswerForm;
import nedis.study.jee.forms.QuestionEditForm;
import nedis.study.jee.forms.TestForm;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.services.TutorService;
import nedis.study.jee.utils.ReflectionUtils;
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

	//NEDIS
	@RequestMapping(value="editTest/id{testId}", method=RequestMethod.GET)
	public String showTestForEdit(Model model,@PathVariable String testId){
		Test test = tutorService.getTest(testId);
		TestForm testForm = new TestForm();
		ReflectionUtils.copyByNotNullFields(testForm, test);
		List<Question> list = test.getQuestions();
		ArrayList<StringId> testQuestions = new ArrayList<StringId>();
		for (Question question : list){
			testQuestions.add(new StringId(question.getIdQuestion(), question.getName()));
		}
		testForm.setTestQuestions(testQuestions);
		model.addAttribute("mode","edit");
		model.addAttribute("testForm", testForm);
		return "tutor/editTest";
	}

	@RequestMapping(value="deleteTest/id{testId}", method=RequestMethod.GET)
	public String deleteTest(Model model,@PathVariable String testId) {
		tutorService.deleteTest(Long.valueOf(testId));
	 return "redirect:/tutor/test";
	}

	@RequestMapping(value="editTest/Ok")
	public String editTest(@ModelAttribute TestForm form){
		tutorService.updateTest(form);

		return "redirect:/tutor/test";
	}

	//NEDIS
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model){
		Account account = commonService.getLoginAccount();
		List<Test> list = tutorService.getTestList(account);
		List<StringId> tests = new ArrayList<StringId>();
		for (Test test : list){
		  tests.add(new StringId(test.getIdTest(),test.getName()));
		}

		model.addAttribute("tests",tests);


		return "tutor/test";
	}
	@RequestMapping(value="/newTest", method=RequestMethod.GET)
	public String showNewTest(Model model){
		TestForm testForm = new TestForm();
		model.addAttribute("mode","new");
		model.addAttribute("testForm", testForm);
		return "tutor/editTest";
	}
	@RequestMapping(value="/addTest")
	public String addNewTest(@ModelAttribute("testForm") TestForm testform){
		tutorService.createTest(testform);
		return "redirect:/tutor/test";
	}
//NEDIS
	@RequestMapping(value="/deleteQuestion")
	public String deleteQuestion(Model model,@RequestParam String testId,@RequestParam String questionId){

		tutorService.deleteQuestion(Long.valueOf(questionId));

		return "redirect:/tutor/editTest/id"+testId;
	}

	@RequestMapping(value="/editQuestion", method=RequestMethod.GET)
	public String showQuestion(Model model,@RequestParam String questionId){
		QuestionEditForm questionEditForm = new QuestionEditForm();
		Question question = tutorService.getQuestion(Long.valueOf(questionId));
		questionEditForm.setQuestionId(question.getIdQuestion());
		questionEditForm.setQuestionName(question.getName());
		questionEditForm.setTestId(question.getTest().getIdTest());
		model.addAttribute("mode", "edit");
		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("questionEditForm", questionEditForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="/editQuestion/Ok", method = RequestMethod.POST)
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {
		//NEDIS
		tutorService.updateQuestion(form, form.getQuestionId());

		return "redirect:/tutor/editTest/id"+form.getTestId();
	}

	@RequestMapping(value="/editQuestion/add", method = RequestMethod.POST)
	public String addQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {

		tutorService.addQuestion(form);

		return "redirect:/tutor/editTest/id"+form.getTestId();
	}

	//NEDIS
	@RequestMapping(value="/editQuestion/new", method = RequestMethod.GET)
	public String addQuestion(Model model,@RequestParam String testId) {
		QuestionEditForm questionEditForm = new QuestionEditForm();
		questionEditForm.setTestId(Long.valueOf(testId));
		model.addAttribute("mode","new");
		model.addAttribute("questionEditForm", questionEditForm);

		return "tutor/editQuestion";
	}

	@RequestMapping(value="/newAnswer/id{questionId}", method=RequestMethod.POST)
	public String addAnswer(Model model,@ModelAttribute("newAnswerForm") NewAnswerForm newAnswerForm){
		tutorService.addAnswer(newAnswerForm);

		return "redirect:/tutor/editQuestion?questionId="+newAnswerForm.getQuestionId();
	}

	@RequestMapping(value="/deleteAnswer")
	public String deleteAnswer(Model model,@RequestParam String answerId,@RequestParam String questionId){

		tutorService.deleteAnswer(Long.valueOf(answerId));

		return "redirect:/tutor/editQuestion?questionId="+questionId;
	}

	@RequestMapping(value="/newAnswer/id{questionId}", method=RequestMethod.GET)
	public String newAnswer(Model model,@PathVariable String questionId){

		NewAnswerForm newAnswerForm = new NewAnswerForm();
		newAnswerForm.setQuestionId(Long.valueOf(questionId));
		model.addAttribute("newAnswerForm", newAnswerForm);
		return "/tutor/newAnswer";
	}

}
