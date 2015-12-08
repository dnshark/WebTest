package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.entities.AccountRole;
import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.AdminForm;
import nedis.study.jee.forms.Basic;
import nedis.study.jee.forms.CBItem;
import nedis.study.jee.services.AdminService;

import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
	public String showLogin(ModelMap model,@PathVariable String userId){
		//Account user = adminService.getAccount(Long.valueOf(userId));
		//AdminForm adminForm = getAdminForm(model);
		//ReflectionUtils.copyByFields(adminForm, user);
		/*List<AccountRole> list = user.getAccountRoles();
		ArrayList<String> roles = new ArrayList<String>();
		for (AccountRole accountRole : list) {
			roles.add(String.valueOf(accountRole.getRole().getId()));
		} */
		//adminForm.setRole(roles);
		Basic basic = new Basic();

		basic.setName("Basic Data Structure");

		List<CBItem> allItems = new ArrayList<CBItem>();
		allItems.add(new CBItem("First"));
		allItems.add(new CBItem("Second"));
		allItems.add(new CBItem("Third"));
		allItems.add(new CBItem("Fourth"));

		model.addAttribute("allItems", allItems);

		List<CBItem> cbItems = new ArrayList<CBItem>();
		cbItems.add(new CBItem("First"));
		cbItems.add(new CBItem("Third"));
		basic.setCbItems(cbItems);

		model.addAttribute("basic", basic);

		return "admin/demo";
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
