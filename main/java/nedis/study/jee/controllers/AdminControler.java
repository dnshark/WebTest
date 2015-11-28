package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.AdminService;

import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
@RequestMapping("/admin")
public class AdminControler extends AbstractController {

	@Autowired
	protected AdminService adminService;
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(){
		return "admin/home";
	}

	@RequestMapping(value="/listUsers", method=RequestMethod.GET)
	public String showTest(Model model){
		model.addAttribute("users", adminService.loadAllUser());
		return "admin/listUsers";
	}

	@RequestMapping(value="/id{userId}", method=RequestMethod.GET)
	public String showLogin(Model model,@PathVariable String userId){
		Account user = adminService.getAccount(Long.valueOf(userId));
		UserForm userForm = new UserForm();
		ReflectionUtils.copyByFields(userForm, user);
		model.addAttribute("userForm", userForm);

		return "admin/userInfo";
	}

}
