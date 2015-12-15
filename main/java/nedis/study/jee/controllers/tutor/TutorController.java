package nedis.study.jee.controllers.tutor;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.forms.tutor.NewAnswerForm;
import nedis.study.jee.forms.tutor.QuestionEditForm;
import nedis.study.jee.forms.tutor.TestForm;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.security.SecurityUtils;
import nedis.study.jee.services.tutor.TutorService;
import nedis.study.jee.services.tutor.impl.AdvancedTutorServiceImpl;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
@RequestMapping("/tutor")
public class TutorController extends AbstractController {

	@Autowired
	@Qualifier("tutorService")
	private TutorService tutorService;

	@Autowired
	@Qualifier("advancedTutorService")
	private TutorService advancedTutorService;

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model){
		model.addAttribute("helo",getService().getHelo());
		return "tutor/home";
	}

	private TutorService getService(){
		if (SecurityUtils.getCurrentAccount().getRole() == ApplicationConstants.ADVANCED_TUTOR_ROLE)
		return advancedTutorService;

		return tutorService;
	}

	//NEDIS
	@RequestMapping(value="edit/test/id{testId}", method=RequestMethod.GET)
	public String showTestForEdit(Model model,@PathVariable Long testId){
		Test test = getService().getTest(testId);
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
	public String deleteTest(Model model,@PathVariable Long testId,HttpServletRequest request) {
		Account account = commonService.getLoginAccount();
		try {
			getService().deleteTest(testId, account);
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url="+request.getRequestURI();
		}
		return "redirect:/tutor/test?page=0&count=50";
	}

	@RequestMapping(value="edit/test/ok")
	public String editTest(@ModelAttribute TestForm form,HttpServletRequest request){
		Account account = commonService.getLoginAccount();
		try {
			getService().updateTest(form, account);
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url="+request.getRequestURI();
		}

		return "redirect:/tutor/test?page=0&count=50";
	}

	//NEDIS
	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model,@RequestParam int page, int count){
		Account account = commonService.getLoginAccount();
		List<Test> list = getService().getTestList(account, page, count);
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
		getService().createTest(testform);
		return "redirect:/tutor/test?page=0&count=50";
	}
//NEDIS
	@RequestMapping(value="/delete/question")
	public String deleteQuestion(Model model,@RequestParam Long questionId,HttpServletRequest request){

		Account account = commonService.getLoginAccount();

		try {
			Test test = getService().deleteQuestion(questionId, account);
			return "redirect:/tutor/edit/test/id"+test.getIdTest();
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url="+request.getRequestURI();
		}

	}

	@RequestMapping(value="/edit/question", method=RequestMethod.GET)
	public String showQuestion(Model model,@RequestParam Long questionId){
		Question question = getService().getQuestion(questionId);
		QuestionEditForm questionEditForm = getService().fillQuestionEditForm(question);
		model.addAttribute("mode", "edit");
		model.addAttribute("answers", question.getAnswers());
		model.addAttribute("questionEditForm", questionEditForm);
		return "tutor/editQuestion";
	}

	@RequestMapping(value="/edit/question/ok", method = RequestMethod.POST)
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form,HttpServletRequest request) {
		Account account = commonService.getLoginAccount();
		try {
			getService().updateQuestion(form, account);
			return "redirect:/tutor/edit/test/id"+form.getTestId();
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url="+request.getRequestURI();
		}
	}

	@RequestMapping(value="/edit/question/add", method = RequestMethod.POST)
	public String addQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form,HttpServletRequest request) {
		Account account = commonService.getLoginAccount();
		try {
			getService().addQuestion(form, account);
			return "redirect:/tutor/edit/test/id"+form.getTestId();
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url=" + request.getRequestURI();
		}
	}

	@RequestMapping(value="/edit/question/new", method = RequestMethod.GET)
	public String addQuestion(Model model,@RequestParam Long testId) {
		QuestionEditForm questionEditForm = getService().getQuestionEditForm(testId);
		model.addAttribute("mode","new");
		model.addAttribute("questionEditForm", questionEditForm);

		return "tutor/editQuestion";
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.POST)
	public String addAnswer(Model model,@ModelAttribute("newAnswerForm") NewAnswerForm newAnswerForm,HttpServletRequest request){
		Account account = commonService.getLoginAccount();
		try {
			getService().addAnswer(newAnswerForm, account);
			return "redirect:/tutor/edit/question?questionId="+newAnswerForm.getQuestionId();
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url=" + request.getRequestURI();
		}

	}

	@RequestMapping(value="/delete/answer")
	public String deleteAnswer(Model model,@RequestParam Long answerId,@RequestParam String questionId,HttpServletRequest request){
		Account account = commonService.getLoginAccount();
		try {
			getService().deleteAnswer(answerId, account);
			return "redirect:/tutor/edit/question?questionId="+questionId;
		} catch (Exception e) {
			LOGGER.error(e);
			return "redirect:/error?url=" + request.getRequestURI();
		}
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.GET)
	public String newAnswer(Model model,@PathVariable Long questionId){

		NewAnswerForm newAnswerForm = new NewAnswerForm();
		newAnswerForm.setQuestionId(questionId);
		model.addAttribute("newAnswerForm", newAnswerForm);
		return "/tutor/newAnswer";
	}

}
