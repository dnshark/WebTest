package nedis.study.jee.services.impl;

import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.EmailService;
import nedis.study.jee.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Dmitrij on 26.11.2015.
 */
@Service("templateService")
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private Settings emailSettings;

    @Autowired
    private EmailService emailService;

    private String readTemplate(String filename) throws FileNotFoundException {
        String text = "";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/"+filename);
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine())
            text= text.concat(scanner.nextLine());
        scanner.close();
        return text;
    }

    public String getTemplateForEmail(UserForm form, String filename) throws FileNotFoundException {
        String text = readTemplate(filename);

        Map<String, Object> map = getMapParams(form);

        return resolveVariables(text,map);
    }

    @Override
    public void sendVerificationEmail(UserForm form) throws FileNotFoundException, MessagingException {
        String content = getTemplateForEmail(form, emailSettings.getVerificationEmailFileName());
        emailService.sendVerificationEmail(form.getEmail(), form.getFio(), content);
    }

    @Override
    public void sendRestoreEmail(UserForm form) throws FileNotFoundException, MessagingException {
        String content = getTemplateForEmail(form, emailSettings.getRestoreFileName());
        emailService.sendRestoreEmail(form.getEmail(), form.getFio(), content);
    }

    private Map<String, Object> getMapParams(UserForm form) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", form.getFio());
        params.put("password", form.getPassword());
        params.put("login", form.getLogin());
        String host = emailSettings.getHost();
        params.put("host_context", host+"/hash"+form.getHash());
        return params;
    }

    public static String resolveVariables(String text,Map<String,Object> params) {
        String result = text;
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String var = "${" + entry.getKey() + "}";
            String value = entry.getValue() == null ? "" : entry.getValue().toString();
            result = result.replace(var, value);
        }

        return result;
    }

}
