package nedis.study.jee.controllers.allAccess;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Role;
import nedis.study.jee.security.CurrentAccount;
import nedis.study.jee.security.SecurityUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class CommonControler extends AbstractController implements InitializingBean {

    private final Map<Integer, String> redirects = new HashMap<Integer, String>();

    @Override
    public void afterPropertiesSet() throws Exception {
        redirects.put(ApplicationConstants.ADMIN_ROLE, "/admin/home");
        redirects.put(ApplicationConstants.ADVANCED_TUTOR_ROLE, "/tutor/home");
        redirects.put(ApplicationConstants.TUTOR_ROLE, "/tutor/home");
        redirects.put(ApplicationConstants.STUDENT_ROLE, "/home");
    }

    protected void initRoles(Model model) {
        List<Role> roles = commonService.listAllRoles();
        model.addAttribute("roles", roles);
    }

    @RequestMapping(value = {"/login", "/loginFailed"}, method = RequestMethod.GET)
    public String showLogin(Model model) {
        initRoles(model);
        return "login";
    }

    @RequestMapping(value = {"/myInfo"}, method = RequestMethod.GET)
    public String myInfo(Model model) {
        CurrentAccount currentAccount = SecurityUtils.getCurrentAccount();
        return "redirect:" + redirects.get(currentAccount.getRole());
    }

}
