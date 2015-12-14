package nedis.study.jee.controllers.allAccess;

import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.allAccess.AllAccessService;
import nedis.study.jee.services.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Controller
public class AllAccessController extends AbstractController {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AllAccessService allAccessService;
    @RequestMapping(value="result", method= RequestMethod.GET)
    public String showResults(Model model,@RequestParam int offSet,int count){
        Account account = commonService.getLoginAccount();
        model.addAttribute("results",studentService.listAllResult(account,offSet,count));
        return "/allAccess/result";
    }

    @RequestMapping(value="info", method= RequestMethod.GET)
    public String showInfo(Model model,HttpSession session){
        Account account = commonService.getLoginAccount();
        model.addAttribute("account",account);
        return "/allAccess/info";
    }

    @RequestMapping(value="edit/info", method=RequestMethod.GET)
    public String showEditInfo(Model model,HttpSession session){

        Account account = commonService.getLoginAccount();
        UserForm userForm = commonService.getUserForm(account);

        model.addAttribute("userForm", userForm);
        return "/allAccess/editInfo";
    }

    @RequestMapping(value="edit/info/ok", method=RequestMethod.POST)
    public String updateUser(Model model,@ModelAttribute("userForm") UserForm form){
        Account account = commonService.getLoginAccount();
        allAccessService.fillForm(form, account);

        model.addAttribute("userForm", form);
        return "/allAccess/info";
    }


}
