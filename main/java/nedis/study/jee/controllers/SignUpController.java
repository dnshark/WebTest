package nedis.study.jee.controllers;

import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.SignUpForm;
import nedis.study.jee.services.CommonService;
import nedis.study.jee.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;

/**
 * Created by Dmitrij on 25.11.2015.
 */
@Controller
public class SignUpController extends AbstractController{

    @Autowired
    protected CommonService commonService;

    @Autowired
    protected TemplateService templateService;

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String showLogin(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @RequestMapping(value="/signup/ok", method= RequestMethod.POST)
    public String DoSignUp(@ModelAttribute("signUpForm") SignUpForm form, BindingResult result) throws InvalidUserInputException {
        try {
            commonService.signUp(form);
            return "redirect:/login";
        } catch (MessagingException e) {
            result.addError(new ObjectError("Can't send e-mail", e.getMessage()));
            LOGGER.info("send e-mail Error " + e.getMessage());
            return "/signup";
            }
         catch (InvalidUserInputException e) {
             result.addError(new ObjectError("Can't create user. Change some information.", e.getMessage()));
             LOGGER.info("Input form Error " + e.getMessage());
             return "/signup";
         } catch (FileNotFoundException e) {
            result.addError(new ObjectError("Can't find e-mail template file.", e.getMessage()));
            LOGGER.info("Can't find e-mail template file " + e.getMessage());
            return "/signup";
        }
    }

}
