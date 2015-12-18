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
    Cleaner cleaner;
    	 @Scheduled(cron = "0/10 * * * * *")
    	 public void pollDatabase() {
             cleaner.clearNotConfirmedUsers();
         }
}
