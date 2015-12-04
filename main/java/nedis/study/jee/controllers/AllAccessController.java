package nedis.study.jee.controllers;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.AllAccessService;
import nedis.study.jee.services.StudentService;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Controller
@RequestMapping(value="/allAccess")
public class AllAccessController extends AbstractController {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AllAccessService allAccessService;
    @RequestMapping(value="result", method= RequestMethod.GET)
    public String showResults(Model model,HttpSession session){
        Account account = commonService.getLoginAccount();
        model.addAttribute("results",studentService.listAllResult(account));
        return "allAccess/result";
    }

    @RequestMapping(value="info", method= RequestMethod.GET)
    public String showInfo(Model model,HttpSession session){
        Account account = commonService.getLoginAccount();
        model.addAttribute("account",account);
        return "allAccess/info";
    }

    @RequestMapping(value="editInfo", method=RequestMethod.GET)
    public String showEditInfo(Model model,HttpSession session){
        UserForm userForm = new UserForm();
        Account account = commonService.getLoginAccount();
        ReflectionUtils.copyByFields(userForm, account);
        model.addAttribute("userForm", userForm);
        return "allAccess/editInfo";
    }

    @RequestMapping(value="editInfoOk", method=RequestMethod.POST)
    public String updateUser(Model model,@ModelAttribute("userForm") UserForm form){
        Account account = commonService.getLoginAccount();
        allAccessService.copyFormToUser(form,account);
        commonService.updateAccount(account);
        model.addAttribute("userForm", form);
        return "allAccess/editInfo";
    }


}
