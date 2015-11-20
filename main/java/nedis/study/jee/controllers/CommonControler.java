package nedis.study.jee.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.LoginForm;
import nedis.study.jee.services.CommonService;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class CommonControler extends AbstractController implements InitializingBean {
	
	private final Map<Integer, String> redirects = new HashMap<Integer, String>();
	@Override
	public void afterPropertiesSet() throws Exception {
		redirects.put(ApplicationConstants.ADMIN_ROLE, "/admin/home");
		redirects.put(ApplicationConstants.ADVANCED_TUTOR_ROLE, "/advanced_tutor/home");
		redirects.put(ApplicationConstants.TUTOR_ROLE, "/tutor/home");
		redirects.put(ApplicationConstants.STUDENT_ROLE, "/home");
	}
	
	@Autowired
	protected CommonService commonService;
	
	@Autowired
    @Qualifier("loginFormValidator")
    private Validator validator;
 
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setValidator(validator);
    }
    
    @RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result, 
						Model model, HttpSession session){
		try {
			if(result.hasErrors()) {
				initRoles(model);
				return "login";
			}
			Account a = commonService.login(loginForm.getLogin(), loginForm.getPassword(), loginForm.getIdRole());
			session.setAttribute("CURRENT_ACCOUNT", a);
			return "redirect:"+redirects.get(loginForm.getIdRole());
		} catch (InvalidUserInputException e) {
			result.addError(new ObjectError("", e.getMessage()));
			initRoles(model);
			return "login";
		}
	}
    
    protected void initRoles(Model model){
    	List<Role> roles = commonService.listAllRoles();
		model.addAttribute("roles", roles);
    }
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String showLogin(Model model){
		model.addAttribute("loginForm", new LoginForm());
		initRoles(model);
		return "login";
	}
	
	
}
