package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.AdminForm;
import nedis.study.jee.security.CurrentAccount;
import nedis.study.jee.security.SecurityUtils;
import nedis.study.jee.services.AdminService;

import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
	public String showLogin(Model model,@PathVariable Long userId){
		Account user = adminService.getAccount(userId);
		AdminForm adminForm = getAdminForm(model);


		adminForm.setCheckRoles(adminService.getRoles(user));

		model.addAttribute("allRoles", commonService.listAllRoles());
		model.addAttribute("adminForm",adminForm);
		model.addAttribute("mode","edit");
		model.addAttribute("adminId",userId);
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
		ReflectionUtils.copyByFields(adminForm, user);
		return adminForm;
	}
	@RequestMapping(value="/update{adminId}")
	public String DoUpdateInfo(Model model,@PathVariable String adminId,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result){
		adminService.updateUser(Long.valueOf(adminId), adminForm);
		return "redirect:id"+adminId;
	}

	@RequestMapping(value="/delete{adminId}", method = RequestMethod.POST)
	public String DoDeleteInfo(Model model,@PathVariable String adminId){
			adminService.deleteUser(Long.valueOf(adminId));
			return "redirect:listUsers";
	}

	@RequestMapping(value="/add{adminId}", method = RequestMethod.POST)
	public String DoAddInfo(Model model,@PathVariable String adminId,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result) {
		adminService.addUser(adminForm);
		return "redirect:listUsers";
	}

}
