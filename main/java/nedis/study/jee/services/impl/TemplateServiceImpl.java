package nedis.study.jee.services.impl;

import nedis.study.jee.forms.SignUpForm;
import nedis.study.jee.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
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

    private String readTemplate() throws FileNotFoundException {
        String text = "";
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("/"+emailSettings.getEmailFileName());
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNextLine())
            text= text.concat(scanner.nextLine());
        scanner.close();
        return text;
    }

    public String GetTemplateForEmail(SignUpForm form) throws FileNotFoundException, UnknownHostException {
        String text = readTemplate();

        Map<String, Object> map = getMapParams(form);

        return resolveVariables(text,map);
    }

    private Map<String, Object> getMapParams(SignUpForm form) throws UnknownHostException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user", form.getFio());
        params.put("password", form.getPassword());
        params.put("login", form.getLogin());
        String host = InetAddress.getLocalHost().getHostAddress()+":"+emailSettings.getPort();
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
