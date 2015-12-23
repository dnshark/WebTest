package nedis.study.jee.controllers.allAccess;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.User;
import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.controllers.AbstractController;
import nedis.study.jee.entities.Account;
import nedis.study.jee.security.SecurityUtils;
import nedis.study.jee.services.allAccess.CommonService;
import nedis.study.jee.services.allAccess.Settings;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 * @author nedis
 * @version 1.0
 */
@Controller
public class FacebookController extends AbstractController implements InitializingBean {

    @Value("${facebook.clientId}")
    private String facebookClientId;

    @Value("${facebook.secretKey}")
    private String facebookSecretKey;

    @Autowired
    private Settings emailSettings;

    @Autowired
    private CommonService commonService;

    private String fbReferrer;

    @Override
    public void afterPropertiesSet() throws Exception {
        fbReferrer = "https://graph.facebook.com/oauth/authorize?client_id=" + facebookClientId +
                "&redirect_uri=http://" + emailSettings.getHost() + "/fromfb" + "&scope=email,user_location,user_birthday";
    }

    @RequestMapping(value = {"/fbLogin", "/fbSignup"}, method = {RequestMethod.GET})
    public String fbLogin() throws Exception {
        return "redirect:" + fbReferrer;
    }

    @RequestMapping(value = "/fromfb", method = {RequestMethod.GET})
    public String fromfb(@RequestParam("code") String code) throws Exception {
        User user = getFacebookUser(code);
        Account a = commonService.login(user);
        SecurityUtils.authentificate(a, ApplicationConstants.STUDENT_ROLE);
        return "redirect:/home";
    }


    protected User getFacebookUser(String code) throws IOException {
        String url = "https://graph.facebook.com/oauth/access_token?client_id="
                + facebookClientId + "&redirect_uri=http://" + emailSettings.getHost() + "/fromfb?referrer="
                + fbReferrer + "&client_secret=" + facebookSecretKey + "&code=" + code;
        URLConnection connection = new URL(url).openConnection();
        InputStream in = null;
        try {
            in = connection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\Z");
            String out = scanner.next();
            String[] auth1 = out.split("=");
            String[] auth2 = auth1[1].split("&");
            FacebookClient facebookClient = new DefaultFacebookClient(auth2[0], Version.LATEST);
            User user = facebookClient.fetchObject("me", User.class, Parameter.with("fields", "id,name,email,locale"));
            return user;
        } finally {
            IOUtils.closeQuietly(in);
        }
    }
}
