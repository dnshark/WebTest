package nedis.study.jee.controllers.allAccess;

import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.allAccess.CommonService;
import nedis.study.jee.services.allAccess.SignUpService;
import nedis.study.jee.services.allAccess.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dmitrij on 25.11.2015.
 */
@Controller
public class SignUpController extends AbstractController {

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected SignUpService signUpService;

    @Autowired
    protected TemplateService templateService;

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String showLogin(Model model){
        model.addAttribute("signUpForm", new UserForm());
        return "signup";
    }

    @RequestMapping(value="/hash/{hashText}", method=RequestMethod.GET)
    public String doConfirmRegister(Model model,@PathVariable String hashText){
        Account account =  signUpService.getAccountByHash(hashText);
        if (account==null) {
            model.addAttribute("confirmed","Incorrect link");
        }else if (account.getConfirmed()){
            model.addAttribute("confirmed","Account already confirmed");
        }else {
            signUpService.confirmAccount(account);
            model.addAttribute("confirmed","Congradulation account confirmed");
        }

        return "message";
    }

    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public String doSignUp(Model model,@ModelAttribute("signUpForm") UserForm form, BindingResult result) throws InvalidUserInputException {
        try {
            commonService.signUp(form,true);
            model.addAttribute("confirmed","Check email to confirm password");
            return "message";

        } catch (Exception e) {
            result.addError(new ObjectError("Can't send e-mail", e.getMessage()));
            LOGGER.info("Error sign up " + e.getMessage());
            return "/signup";
        }

    }

}
