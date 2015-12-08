package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.AdminForm;
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
		AdminForm adminForm = getAdminForm(model);
		ReflectionUtils.copyByFields(adminForm, user);

		adminForm.setCheckRoles(adminService.getRoles(user));

		model.addAttribute("allRoles", commonService.listAllRoles());
		model.addAttribute("adminForm",adminForm);
		model.addAttribute("mode","edit");
		return "admin/userInfo";
	}

	@RequestMapping(value="userInfo/new", method=RequestMethod.GET)
	public String showLogin(Model model){
		AdminForm adminForm = getAdminForm(model);
		model.addAttribute("adminForm", adminForm);
		model.addAttribute("mode","new");
		return "admin/userInfo";
	}

	private AdminForm getAdminForm(Model model) {
		AdminForm adminForm = new AdminForm();
		List<Role> list = commonService.listAllRoles();
		model.addAttribute("roles", list);
		return adminForm;
	}

	@RequestMapping(value="/save{adminId}")
	public String DoSaveInfo(@PathVariable String adminId,@ModelAttribute("adminForm") AdminForm form) {
			adminService.updateUser(Long.valueOf(adminId), form);
			return "redirect:id"+adminId;
	}

	@RequestMapping(value="/delete{adminId}")
	public String DoDeleteInfo(@PathVariable String adminId,@ModelAttribute("adminForm") AdminForm form) {
			adminService.deleteUser(Long.valueOf(adminId));
			return "redirect:listUsers";
	}

	@RequestMapping(value="/add")
	public String DoAddInfo(@ModelAttribute("adminForm") AdminForm form) {
			adminService.addUser(form);
			return "redirect:listUsers";
	}

}
