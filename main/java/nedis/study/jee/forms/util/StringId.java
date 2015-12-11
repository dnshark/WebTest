package nedis.study.jee.forms.util;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class StringId {
    private String id;
    private String name;

    public StringId(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
