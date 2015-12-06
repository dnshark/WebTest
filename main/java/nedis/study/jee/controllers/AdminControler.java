package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.AdminService;

import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
		UserForm userForm = getUserForm(model);
		ReflectionUtils.copyByFields(userForm, user);
		model.addAttribute("userForm", userForm);
		model.addAttribute("adminId",userId);
		model.addAttribute("mode","edit");
		return "admin/userInfo";
	}

	@RequestMapping(value="userInfo/new", method=RequestMethod.GET)
	public String showLogin(Model model){
		UserForm userForm = getUserForm(model);
		model.addAttribute("userForm", userForm);
		model.addAttribute("mode","new");
		return "admin/userInfo";
	}

	private UserForm getUserForm(Model model) {
		UserForm userForm = new UserForm();
		List<Role> roles = commonService.listAllRoles();
		model.addAttribute("roles", roles);
		return userForm;
	}

	@RequestMapping(value="/save{adminId}")
	public String DoSaveInfo(@PathVariable String adminId,@ModelAttribute("userForm") UserForm form) {
			adminService.updateUser(Long.valueOf(adminId), form);
			return "redirect:id"+adminId;
	}

	@RequestMapping(value="/delete{adminId}")
	public String DoDeleteInfo(@PathVariable String adminId,@ModelAttribute("userForm") UserForm form) {
			adminService.deleteUser(Long.valueOf(adminId));
			return "redirect:listUsers";
	}

	@RequestMapping(value="/add")
	public String DoAddInfo(@ModelAttribute("userForm") UserForm form) {
			adminService.addUser(form);
			return "redirect:listUsers";
	}

}
