package nedis.study.jee.controllers.allAccess;

import nedis.study.jee.ApplicationConstants;
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

import static nedis.study.jee.utils.HibernateDebugUtils.turnOnShowSQL;

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
    public String showResults(@RequestParam(value = "page", required = false) Integer page,
                              @RequestParam(value = "count", required = false) Integer count,
                              Model model){

        Account account = commonService.getLoginAccount();

       if (page == null) {page= 0;}
       if (count == null) {count= ApplicationConstants.DEFAULT_PAGE_COUNT;}

        model.addAttribute("results",studentService.listAllResult(account,(page-1)*count,count));
        model.addAttribute("maxPages",studentService.getMaxPageResult(account,count));
        model.addAttribute("page", page);
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
