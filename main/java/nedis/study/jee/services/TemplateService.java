package nedis.study.jee.services;

import nedis.study.jee.forms.SignUpForm;

import java.io.FileNotFoundException;

/**
 * Created by Dmitrij on 26.11.2015.
 */
public interface TemplateService {
    String GetTemplateForEmail(SignUpForm form) throws FileNotFoundException;
}
