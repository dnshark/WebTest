package nedis.study.jee.forms.admin;

import nedis.study.jee.entities.Role;
import nedis.study.jee.forms.UserForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 08.12.2015.
 */
public class AdminForm extends UserForm {
    private Boolean confirmed=false;

    private Boolean active=false;

    private List<String> checkRoles;

    public List<String> getCheckRoles() {
        return checkRoles;
    }

    public void setCheckRoles(List<String> checkRoles) {
        this.checkRoles = checkRoles;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
