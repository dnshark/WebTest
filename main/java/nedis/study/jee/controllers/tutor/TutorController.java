package nedis.study.jee.controllers.tutor;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Question;
import nedis.study.jee.entities.Test;
import nedis.study.jee.exceptions.InvalidUserAccessException;
import nedis.study.jee.forms.tutor.NewAnswerForm;
import nedis.study.jee.forms.tutor.QuestionEditForm;
import nedis.study.jee.forms.tutor.TestForm;
import nedis.study.jee.forms.util.StringId;
import nedis.study.jee.security.SecurityUtils;
import nedis.study.jee.services.tutor.TutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

	@RequestMapping(value="edit/test/id{testId}", method=RequestMethod.GET)
	public String showTestForEdit(Model model,@PathVariable Long testId,
								  @RequestParam(value = "page", required = false) Integer page,
								  @RequestParam(value = "count", required = false) Integer count
	){

		if (page == null) {page= 1;}
		if (count == null) {count= ApplicationConstants.DEFAULT_PAGE_COUNT;}


		TestForm testForm = getService().getTestForm(testId, page, count);
		model.addAttribute("mode","edit");
		model.addAttribute("testForm", testForm);
		model.addAttribute("maxPages",getService().getQuestionMaxPageCount(testId, count));
		model.addAttribute("page", page);
		return "tutor/editTest";
	}

	@RequestMapping(value="delete/test/id{testId}", method=RequestMethod.GET)
	public String deleteTest(Model model,@PathVariable Long testId,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().deleteTest(testId, account);

		return "redirect:/tutor/test";
	}

	@RequestMapping(value="edit/test/ok")
	public String editTest(@ModelAttribute TestForm form,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().updateTest(form, account);

		return "redirect:/tutor/test";
	}

	@RequestMapping(value="test", method=RequestMethod.GET)
	public String showTutorTests(Model model, @RequestParam(value = "page", required = false) Integer page,
								 @RequestParam(value = "count", required = false) Integer count
	){

		if (page == null) {page= 1;}
		if (count == null) {count= ApplicationConstants.DEFAULT_PAGE_COUNT;}

		Account account = commonService.getLoginAccount();
		List<StringId> tests = tutorService.getTests(page, count, account);

		model.addAttribute("tests",tests);
		model.addAttribute("maxPages",getService().getTestMaxPageCount(account,count));
		model.addAttribute("page", page);
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
		return "redirect:/tutor/test";
	}

	@RequestMapping(value="/delete/question")
	public String deleteQuestion(Model model,@RequestParam Long questionId,HttpServletRequest request) throws InvalidUserAccessException {

		Account account = commonService.getLoginAccount();

		Test test = getService().deleteQuestion(questionId, account);
		return "redirect:/tutor/edit/test/id"+test.getIdTest();

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
	public String editQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().updateQuestion(form, account);
		return "redirect:/tutor/edit/test/id"+form.getTestId();
	}

	@RequestMapping(value="/edit/question/add", method = RequestMethod.POST)
	public String addQuestion(Model model,@ModelAttribute("questionEditForm") QuestionEditForm form,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().addQuestion(form, account);
		return "redirect:/tutor/edit/test/id"+form.getTestId();
	}

	@RequestMapping(value="/edit/question/new", method = RequestMethod.GET)
	public String addQuestion(Model model,@RequestParam Long testId) {
		QuestionEditForm questionEditForm = getService().getQuestionEditForm(testId);
		model.addAttribute("mode","new");
		model.addAttribute("questionEditForm", questionEditForm);

		return "tutor/editQuestion";
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.POST)
	public String addAnswer(Model model,@ModelAttribute("newAnswerForm") NewAnswerForm newAnswerForm,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().addAnswer(newAnswerForm, account);
		return "redirect:/tutor/edit/question?questionId="+newAnswerForm.getQuestionId();

	}

	@RequestMapping(value="/delete/answer")
	public String deleteAnswer(Model model,@RequestParam Long answerId,@RequestParam String questionId,HttpServletRequest request) throws InvalidUserAccessException {
		Account account = commonService.getLoginAccount();

		getService().deleteAnswer(answerId, account);
		return "redirect:/tutor/edit/question?questionId="+questionId;
	}

	@RequestMapping(value="/new/answer/id{questionId}", method=RequestMethod.GET)
	public String newAnswer(Model model,@PathVariable Long questionId){

		NewAnswerForm newAnswerForm = new NewAnswerForm();
		newAnswerForm.setQuestionId(questionId);
		model.addAttribute("newAnswerForm", newAnswerForm);
		return "/tutor/newAnswer";
	}

}
