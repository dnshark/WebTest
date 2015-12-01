package nedis.study.jee.forms;



/**
 * @author nedis
 * @version 1.0
 */
public class UserForm extends AbstractLoginForm implements IForm {
	private static final long serialVersionUID = -3633827335080843887L;
	
	private String email;

	private String fio;

	private String hash;

	private Boolean confirmed=false;

	private Boolean active=false;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getConfirmed() {
		return confirmed;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public void setConfirmed(Boolean confirmed) {
		this.confirmed = confirmed;
	}

}
