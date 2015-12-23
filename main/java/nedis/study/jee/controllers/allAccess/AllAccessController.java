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

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Controller
public class AllAccessController extends AbstractController {
    @Autowired
    protected StudentService studentService;

    @Autowired
    protected AllAccessService allAccessService;

    @RequestMapping(value = "result", method = RequestMethod.GET)
    public String showResults(@RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(value = "count", required = false, defaultValue = ApplicationConstants.DEFAULT_PAGE_COUNT) Integer count,
                              Model model) {

        Account account = commonService.getLoginAccount();

        model.addAttribute("results", studentService.listAllResult(account, (page - 1) * count, count));
        model.addAttribute("maxPages", studentService.getMaxPageResult(account, count));
        model.addAttribute("page", page);
        return "/allAccess/result";
    }


    @RequestMapping(value = "info", method = RequestMethod.GET)
    public String showInfo(Model model) {
        Account account = commonService.getLoginAccount();
        model.addAttribute("account", account);
        return "/allAccess/info";
    }

    @RequestMapping(value = "edit/info", method = RequestMethod.GET)
    public String showEditInfo(Model model) {

        Account account = commonService.getLoginAccount();
        UserForm userForm = commonService.getUserForm(account);

        model.addAttribute("userForm", userForm);
        return "/allAccess/editInfo";
    }

    @RequestMapping(value = "edit/info/ok", method = RequestMethod.POST)
    public String updateUser(Model model, @ModelAttribute("userForm") UserForm form) {
        Account account = commonService.getLoginAccount();
        allAccessService.updateAccountByForm(form, account);

        model.addAttribute("account", account);
        return "/allAccess/info";
    }


}
