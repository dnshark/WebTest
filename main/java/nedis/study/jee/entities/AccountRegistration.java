package nedis.study.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the account_temp_pwd database table.
 * 
 */
@Entity
@Table(name="account_registration")
public class AccountRegistration extends AbstractEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_TEMP_PWD_IDACCOUNT_GENERATOR", sequenceName="account_registration_id_account_registration_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ACCOUNT_TEMP_PWD_IDACCOUNT_GENERATOR")
	@Column(name="id_account_registration", unique=true, nullable=false)
	private Long idAccountRegistration;

	@Column(name="hash", nullable=false, length=255)
	private String hash;

	
	//bi-directional one-to-one association to Account
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_account", nullable=false)
	//FIXME!!!!!!!!! Hibernate does not support OneToOne lazy mode
	private Account account;

    public AccountRegistration() {
    }

	public Long getIdAccountRegistration() {
		return this.idAccountRegistration;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdAccountRegistration();
	}

	public void setIdAccountRegistration(Long idAccount) {
		this.idAccountRegistration = idAccount;
	}

	public String getHash() {
		return this.hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}