package nedis.study.jee.controllers.allAccess;

import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.exceptions.InvalidUserInputException;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.allAccess.SignUpService;
import nedis.study.jee.services.allAccess.TemplateService;
import nedis.study.jee.utils.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Controller
public class ForgetController extends AbstractController {
    @Autowired
    protected SignUpService signUpService;

    @Autowired
    private TemplateService templateService;

    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String showForget(Model model) {
        model.addAttribute("signUpForm", new UserForm());
        return "forget";
    }

    @RequestMapping(value = "/forget", method = RequestMethod.POST)
    public String doSignUp(Model model, @ModelAttribute("signUpForm") UserForm form, BindingResult result) throws InvalidUserInputException {
        Account account = signUpService.getAccountByEmail(form.getEmail());
        try {
            if (account != null) {
                ReflectionUtils.copyByFields(form, account);
                templateService.sendRestoreEmail(form);
                model.addAttribute("confirmed", "Check email");
            } else
                model.addAttribute("confirmed", "No email found");
            return "message";
        } catch (Exception e) {
            result.addError(new ObjectError("Can't send e-mail", e.getMessage()));
            LOGGER.info("send e-mail Error " + e.getMessage());
            return "redirect:forget";
        }
    }
}
