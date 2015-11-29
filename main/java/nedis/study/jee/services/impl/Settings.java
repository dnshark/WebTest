package nedis.study.jee.services.impl;

import org.springframework.stereotype.Repository;

/**
 * Created by Дмитрий on 28.11.2015.
 */
@Repository
public class Settings {

    private String port;

    private String emailReplay;

    private String emailName;

    private String subject;

    public String getEmailReplay() {
        return emailReplay;
    }

    public void setEmailReplay(String emailReplay) {
        this.emailReplay = emailReplay;
    }

    public String getEmailFileName() {
        return emailName;
    }

    public void setEmailFileName(String emailFileName) {
        this.emailName = emailFileName;
    }


    public String getHost() {
        return port;
    }



    public void setPort(String port) {
        this.port = port;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    private String fromName;


    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}
