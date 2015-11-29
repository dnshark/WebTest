package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.AdminService;

import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		model.addAttribute("adminId",userId);
		model.addAttribute("mode","edit");
		return "admin/userInfo";
	}

	@RequestMapping(value="userInfo/new", method=RequestMethod.GET)
	public String showLogin(Model model){
		UserForm userForm = new UserForm();
		model.addAttribute("mode","new");  //NEDIS спросить как лучше сделать отображение кнопок
		model.addAttribute("userForm", userForm);
		return "admin/userInfo";
	}

	@RequestMapping(value="/Ok{adminId}", method= RequestMethod.POST)
	public String DoEditInfo(Model model,@RequestParam String button,@PathVariable String adminId,@ModelAttribute("userForm") UserForm form) {
		if (button.equals("save")) {
			adminService.updateUser(Long.valueOf(adminId), form);
			return "redirect:id"+adminId;
		}
		else if (button.equals("delete")){
			adminService.deleteUser(Long.valueOf(adminId));
			return "admin/listUsers";
		} else if (button.equals("add")){
			Account account = adminService.addUser(form);
		//	return "redirect:id"+String.valueOf(account.getIdAccount());
			return "admin/listUsers";
		}
			 return ""; //to NEDIS (ошибка не определена кнопка)
	}

}
