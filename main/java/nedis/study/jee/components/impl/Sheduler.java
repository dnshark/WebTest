package nedis.study.jee.components.impl;

import nedis.study.jee.services.other.Cleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Дмитрий on 18.12.2015.
 */
@Component
public class Sheduler {

    @Autowired
    private Cleaner cleaner;

    @Scheduled(cron = "0 0 0/12 * * *")
    public void clearNotConfirmedUsers() {
        cleaner.clearNotConfirmedUsers();
    }
}
