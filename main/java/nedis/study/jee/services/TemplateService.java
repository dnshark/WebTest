package nedis.study.jee.services;

import nedis.study.jee.forms.UserForm;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

/**
 * Created by Dmitrij on 26.11.2015.
 */
public interface TemplateService {
    String GetTemplateForEmail(UserForm form) throws FileNotFoundException, UnknownHostException;
}
