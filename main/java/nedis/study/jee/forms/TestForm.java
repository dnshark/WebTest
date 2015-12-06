package nedis.study.jee.forms;

import nedis.study.jee.entities.Answer;
import nedis.study.jee.entities.Test;

import java.util.List;

/**
 * Created by Дмитрий on 02.12.2015.
 */
public class TestForm implements IForm{

    private Test test;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
