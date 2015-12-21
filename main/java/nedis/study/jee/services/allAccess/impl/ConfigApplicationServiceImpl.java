package nedis.study.jee.services.allAccess.impl;

import nedis.study.jee.services.allAccess.ConfigApplicationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author nedis
 * @version 1.0
 */
@Service
public class ConfigApplicationServiceImpl implements ConfigApplicationService {

    @Value("${nedis.jee.study.supportEmailAddress}")
    private String supportEmailAddress;

    @Value("${nedis.jee.study.cssJsVersion}")
    private String cssJsVersion;

    @Override
    public String getSupportEmailAddress() {
        return supportEmailAddress;
    }

    @Override
    public String getCssJsVersion() {
        return cssJsVersion;
    }

}
