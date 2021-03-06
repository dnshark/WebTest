package nedis.study.jee.controllers;

import nedis.study.jee.services.allAccess.CommonService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nedis
 * @version 1.0
 */
public abstract class AbstractController {
    protected final Logger LOGGER = Logger.getLogger(getClass());

    @Autowired
    protected CommonService commonService;
}
