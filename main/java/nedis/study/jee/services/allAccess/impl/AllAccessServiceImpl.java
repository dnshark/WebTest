package nedis.study.jee.services.allAccess.impl;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;
import nedis.study.jee.services.allAccess.AllAccessService;
import nedis.study.jee.services.allAccess.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Дмитрий on 01.12.2015.
 */
@Service("AllAccessService")
public class AllAccessServiceImpl implements AllAccessService {

    @Autowired
    private CommonService commonService;

    public void copyFormToUser(UserForm form, Account account) {
        account.setEmail(form.getEmail());
        account.setLogin(form.getLogin());
        account.setFio(form.getFio());
        if (!form.getPassword().equals("")) {
            account.setPassword(form.getPassword());
        }
    }

    @Override
    public void updateAccountByForm(UserForm form, Account account) {
        copyFormToUser(form, account);
        commonService.updateAccount(account);
    }
}
