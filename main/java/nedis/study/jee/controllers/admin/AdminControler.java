package nedis.study.jee.controllers.admin;

import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.admin.AdminForm;
import nedis.study.jee.services.admin.AdminService;

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

	@RequestMapping(value="/list/users", method=RequestMethod.GET)
	public String showTest(Model model,@RequestParam int offSet, int count){
		model.addAttribute("users", adminService.loadAllUser(offSet,count));
		return "admin/listUsers";
	}

	@RequestMapping(value="user/id{userId}", method=RequestMethod.GET)
	public String showLogin(Model model,@PathVariable Long userId){
		Account user = adminService.getAccount(userId);
		AdminForm adminForm = adminService.getAdminForm(model, user);
		model.addAttribute("adminForm",adminForm);
		model.addAttribute("mode","edit");
		model.addAttribute("adminId",userId);
		return "admin/userInfo";
	}

	@RequestMapping(value="user/info/new", method=RequestMethod.GET)
	public String showLogin(Model model){
		Account user = adminService.buildAccount();
		AdminForm adminForm = adminService.getAdminForm(model, user);
		model.addAttribute("adminForm", adminForm);
		model.addAttribute("mode","new");
		return "admin/userInfo";
	}

	@RequestMapping(value="/update/user/id{userId}")
	public String doUpdateInfo(Model model,@PathVariable Long userId,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result){
		adminService.updateUser(userId, adminForm);
		return "redirect:/admin/list/users?offSet=0&count=50";
	}

	@RequestMapping(value="/delete/user/id{userId}", method = RequestMethod.POST)
	public String doDeleteInfo(Model model,@PathVariable Long userId){
			adminService.deleteUser(userId);
			return "redirect:/admin/list/users?offSet=0&count=50";
	}

	@RequestMapping(value="/add/user", method = RequestMethod.POST)
	public String doAddInfo(Model model,@ModelAttribute("adminForm") AdminForm adminForm, BindingResult result) {
		adminService.addUser(adminForm);
		return "redirect:/admin/list/users?offSet=0&count=50";
	}

}
