package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.AdminForm;
import nedis.study.jee.services.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
	public String showTest(Model model,@RequestParam int offSet, int count){
		model.addAttribute("users", adminService.loadAllUser(offSet,count));
		return "admin/listUsers";
	}

	@RequestMapping(value="user/id{userId}", method=RequestMethod.GET)
	public String showLogin(Model model,@PathVariable Long userId){
		Account user = adminService.getAccount(userId);
		AdminForm adminForm = adminService.getAdminForm(model, user);

		model.addAttribute("allRoles", commonService.listAllRoles());
		model.addAttribute("adminForm",adminForm);
		model.addAttribute("mode","edit");
		model.addAttribute("adminId",userId);
		return "admin/userInfo";
	}

	@RequestMapping(value="userInfo/new", method=RequestMethod.GET)
	public String showLogin(Model model){
		Account user = commonService.getLoginAccount();
		AdminForm adminForm = adminService.getAdminForm(model, user);
		model.addAttribute("adminForm", adminForm);
		model.addAttribute("mode","new");
		return "admin/userInfo";
	}

	@RequestMapping(value="/update/id{adminId}")
	public String doUpdateInfo(Model model,@PathVariable Long adminId,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result){
		adminService.updateUser(adminId, adminForm);
		return "redirect:id"+adminId;
	}

	@RequestMapping(value="/delete/id{adminId}", method = RequestMethod.POST)
	public String doDeleteInfo(Model model,@PathVariable Long adminId){
			adminService.deleteUser(adminId);
			return "redirect:listUsers";
	}

	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String doAddInfo(Model model,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result) {
		adminService.addUser(adminForm);
		return "redirect:listUsers";
	}

}
