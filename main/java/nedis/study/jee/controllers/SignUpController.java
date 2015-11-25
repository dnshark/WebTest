package nedis.study.jee.controllers;

import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.SignUpForm;
import nedis.study.jee.services.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Dmitrij on 25.11.2015.
 */
@Controller
public class SignUpController {

    @Autowired
    protected CommonService commonService;

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String showLogin(Model model){
        model.addAttribute("signUpForm", new SignUpForm());
        return "signup";
    }

    @RequestMapping(value="/signup/ok", method= RequestMethod.POST)
    public String DoSignUp(@ModelAttribute("signUpForm") SignUpForm form) throws InvalidUserInputException {
        try {
            commonService.signUp(form);
        } catch (InvalidUserInputException e) {
            e.printStackTrace();
        }
        return "index";
    }

}
