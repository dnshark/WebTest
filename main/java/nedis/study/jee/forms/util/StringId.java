package nedis.study.jee.forms.util;

/**
 * Created by Дмитрий on 11.12.2015.
 */
public class StringId {
    private Long id;
    private String name;

    public StringId(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
