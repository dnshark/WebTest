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
		model.addAttribute("testForm", testForm);
		return "tutor/editTest";
	}

	@RequestMapping(value="editTest",method = RequestMethod.GET)
	public String editTest(Model model,@ModelAttribute TestForm form){

		TestForm testForm = new TestForm();
		//ReflectionUtils.copyByFields(testForm,test);
		//testForm.setTest(test);}
		return "tutor/editTest";
	}


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
		model.addAttribute("testForm", testForm);
		return "tutor/newTest";
	}
	@RequestMapping(value="/addTest")
	public String addNewTest(@ModelAttribute("testForm") TestForm testform){
		tutorService.createTest(testform);
		return "redirect:/tutor/test";
	}

	@RequestMapping(value="/newAnswer/id{questionId}", method=RequestMethod.GET)
	public String newAnswer(Model model,@PathVariable String questionId){

		NewAnswerForm newAnswerForm = new NewAnswerForm();
		newAnswerForm.setQuestionId(Long.valueOf(questionId));
		model.addAttribute("newAnswerForm", newAnswerForm);
	  return "/tutor/newAnswer";
	}

	@RequestMapping(value="/newAnswer/id{questionId}", method=RequestMethod.POST)
	public String addAnswer(Model model,@ModelAttribute("newAnswerForm") NewAnswerForm newAnswerForm){
		tutorService.addAnswer(newAnswerForm);

		return "redirect:/tutor/editQuestion/id"+String.valueOf(newAnswerForm.getQuestionId());
	}



	@RequestMapping(value="/deleteAnswer")
	public String deleteAnswer(Model model,@RequestParam String questionId,@RequestParam String answerId){

		tutorService.deleteAnswer(Long.valueOf(answerId));

		return "redirect: editQuestion/id"+questionId;
	}

	@RequestMapping(value="/deleteQuestion", method=RequestMethod.POST)
	public String deleteQuestion(Model model,@RequestParam String testId,@RequestParam String questionId){

		tutorService.deleteQuestion(Long.valueOf(questionId));

		return "redirect: editTest/id"+testId;
	}

	@RequestMapping(value="/editQuestion/id{questionId}", method=RequestMethod.GET)
	public String showQuestion(Model model,@PathVariable String questionId){
		QuestionEditForm questionEditForm = new QuestionEditForm();
		Question question = tutorService.getQuestion(Long.valueOf(questionId));

		questionEditForm.setQuestionId(question.getIdQuestion());
		questionEditForm.setQuestionName(question.getName());

		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("questionEditForm", questionEditForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="/editQuestion/Ok", method = RequestMethod.POST)
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {

		tutorService.updateQuestion(form, form.getQuestionId());

		return "tutor/editQuestion";
	}

	@RequestMapping(value="/editQuestion/New", method = RequestMethod.GET)
	public String addQuestion(Model model,@RequestParam String testId) {

	//	tutorService.updateQuestion(form, form.getQuestionId());

		return "tutor/editQuestion";
	}

}
