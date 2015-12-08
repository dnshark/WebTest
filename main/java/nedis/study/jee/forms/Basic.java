package nedis.study.jee.forms;

import java.util.List;

/**
 * Created by Дмитрий on 08.12.2015.
 */
public class Basic implements IForm{

    private String name;

    private List<CBItem> cbItems;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CBItem> getCbItems() {
        return cbItems;
    }

    public void setCbItems(List<CBItem> cbItems) {
        this.cbItems = cbItems;
    }
}
