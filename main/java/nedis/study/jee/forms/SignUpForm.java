package nedis.study.jee.forms;



/**
 * @author nedis
 * @version 1.0
 */
public class SignUpForm extends AbstractLoginForm implements IForm {
	private static final long serialVersionUID = -3633827335080843887L;
	
	private String email;

	private String fio;

	private String hash;

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

}
