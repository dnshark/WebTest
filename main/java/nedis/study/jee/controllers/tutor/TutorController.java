package nedis.study.jee.controllers.tutor;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.tutor.NewAnswerForm;
import nedis.study.jee.forms.tutor.QuestionEditForm;
import nedis.study.jee.forms.tutor.TestForm;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.services.tutor.TutorService;
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
	@RequestMapping(value="edit/test/id{testId}", method=RequestMethod.GET)
	public String showTestForEdit(Model model,@PathVariable Long testId){
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

	@RequestMapping(value="delete/test/id{testId}", method=RequestMethod.GET)
	public String deleteTest(Model model,@PathVariable Long testId) {
		tutorService.deleteTest(testId);
	 return "redirect:/tutor/test";
	}

	@RequestMapping(value="edit/test/Ok")
	public String editTest(@ModelAttribute TestForm form){
		tutorService.updateTest(form);

		return "redirect:/tutor/test?offSet=0&count=50";
	}

	//NEDIS
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model,@RequestParam int offSet, int count){
		Account account = commonService.getLoginAccount();
		List<Test> list = tutorService.getTestList(account,offSet,count);
		List<StringId> tests = new ArrayList<StringId>();
		for (Test test : list){
		  tests.add(new StringId(test.getIdTest(),test.getName()));
		}

		model.addAttribute("tests",tests);


		return "tutor/test";
	}
	@RequestMapping(value="/new/test", method=RequestMethod.GET)
	public String showNewTest(Model model){
		TestForm testForm = new TestForm();
		model.addAttribute("mode","new");
		model.addAttribute("testForm", testForm);
		return "tutor/editTest";
	}
	@RequestMapping(value="/add/test")
	public String addNewTest(@ModelAttribute("testForm") TestForm testform){
		tutorService.createTest(testform);
		return "redirect:/tutor/test?offSet=0&count=50";
	}
//NEDIS
	@RequestMapping(value="/delete/question")
	public String deleteQuestion(Model model,@RequestParam Long questionId){

		Test test = tutorService.deleteQuestion(questionId);

		return "redirect:/tutor/editTest/id"+test.getIdTest();
	}

	@RequestMapping(value="/edit/question", method=RequestMethod.GET)
	public String showQuestion(Model model,@RequestParam Long questionId){
		Question question = tutorService.getQuestion(questionId);
		QuestionEditForm questionEditForm = tutorService.fillQuestionEditForm(question);
		model.addAttribute("mode", "edit");
		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("questionEditForm", questionEditForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="/edit/question/Ok", method = RequestMethod.POST)
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {
		//NEDIS
		tutorService.updateQuestion(form, form.getQuestionId());

		return "redirect:/tutor/editTest/id"+form.getTestId();
	}

	@RequestMapping(value="/edit/question/add", method = RequestMethod.POST)
	public String addQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form) {

		tutorService.addQuestion(form);

		return "redirect:/tutor/editTest/id"+form.getTestId();
	}

	@RequestMapping(value="/edit/question/new", method = RequestMethod.GET)
	public String addQuestion(Model model,@RequestParam Long testId) {
		QuestionEditForm questionEditForm = tutorService.getQuestionEditForm(testId);
		model.addAttribute("mode","new");
		model.addAttribute("questionEditForm", questionEditForm);

		return "tutor/editQuestion";
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.POST)
	public String addAnswer(Model model,@ModelAttribute("newAnswerForm") NewAnswerForm newAnswerForm){
		tutorService.addAnswer(newAnswerForm);

		return "redirect:/tutor/editQuestion?questionId="+newAnswerForm.getQuestionId();
	}

	@RequestMapping(value="/delete/answer")
	public String deleteAnswer(Model model,@RequestParam Long answerId,@RequestParam String questionId){

		tutorService.deleteAnswer(answerId);

		return "redirect:/tutor/editQuestion?questionId="+questionId;
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.GET)
	public String newAnswer(Model model,@PathVariable Long questionId){

		NewAnswerForm newAnswerForm = new NewAnswerForm();
		newAnswerForm.setQuestionId(questionId);
		model.addAttribute("newAnswerForm", newAnswerForm);
		return "/tutor/newAnswer";
	}

}
