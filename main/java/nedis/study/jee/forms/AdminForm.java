package nedis.study.jee.forms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Дмитрий on 08.12.2015.
 */
public class AdminForm extends UserForm {
    private Boolean confirmed=false;

    private Boolean active=false;

    private List<CBItem> cbRoles;

    public List<CBItem> getCbRoles() {
        return cbRoles;
    }

    public void setCbRoles(List<CBItem> cbRoles) {
        this.cbRoles = cbRoles;
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
