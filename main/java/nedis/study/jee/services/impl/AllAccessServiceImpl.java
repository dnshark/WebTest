package nedis.study.jee.services.impl;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.AllAccessService;
import org.springframework.stereotype.Service;

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Service("AllAccessService")
public class AllAccessServiceImpl implements AllAccessService {

    public void copyFormToUser(UserForm form, Account account) {
        account.setEmail(form.getEmail());
        account.setLogin(form.getLogin());
        account.setFio(form.getFio());
        account.setPassword(form.getPassword());
    }
}
