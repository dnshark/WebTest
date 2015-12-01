package nedis.study.jee.services;

import nedis.study.jee.forms.UserForm;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.net.UnknownHostException;

/**
 * Created by Dmitrij on 26.11.2015.
 */
public interface TemplateService {
    String GetTemplateForEmail(UserForm form, String filename) throws FileNotFoundException, UnknownHostException;

    void sendVerificationEmail(UserForm form) throws FileNotFoundException, MessagingException;

    void sendRestoreEmail(UserForm form) throws FileNotFoundException, MessagingException;
}
