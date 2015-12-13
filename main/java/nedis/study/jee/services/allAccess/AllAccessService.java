package nedis.study.jee.services.allAccess;

import nedis.study.jee.entities.Account;
import nedis.study.jee.forms.UserForm;

/**
 * Created by Дмитрий on 01.12.2015.
 */
public interface AllAccessService {
    public void copyFormToUser(UserForm form, Account account);

    void fillForm(UserForm form, Account account);
}
