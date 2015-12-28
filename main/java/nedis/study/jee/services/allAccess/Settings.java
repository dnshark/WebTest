package nedis.study.jee.services.allAccess;

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

    private String restoreFileName;

    private String restoreSubject;

    public String getEmailReplay() {
        return emailReplay;
    }

    public void setEmailReplay(String emailReplay) {
        this.emailReplay = emailReplay;
    }

    public String getVerificationEmailFileName() {
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

    public void setRestoreFileName(String restoreFileName) {
        this.restoreFileName = restoreFileName;
    }

    public String getRestoreFileName() {
        return restoreFileName;
    }

    public void setRestoreSubject(String restoreSubject) {
        this.restoreSubject = restoreSubject;
    }

    public String getRestoreSubject() {
        return restoreSubject;
    }
}
