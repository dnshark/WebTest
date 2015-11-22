package nedis.study.jee.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@Table(name="account")
public class Account extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_IDACCOUNT_GENERATOR", sequenceName="account_id_account_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ACCOUNT_IDACCOUNT_GENERATOR")
	@Column(name="id_account", unique=true, nullable=false)
	private Long idAccount;

	@Column(nullable=false)
	private Boolean active;

	@Column(nullable=false)
	private Timestamp created;

	@Column(nullable=false, length=100)
	private String email;

	@Column(nullable=false, length=60)
	private String login;

	@Column(length=80)
	private String fio;

	@Column(nullable=false, length=255)
	private String password;

	private Timestamp updated;

	//bi-directional many-to-one association to AccountRole
	@OneToMany(mappedBy="account")
	private List<AccountRole> accountRoles;

	//bi-directional one-to-one association to AccountRegistration
	@OneToOne(mappedBy="account", fetch=FetchType.LAZY)
	//FIXME!!!!!!!!! Hibernate does not support OneToOne lazy mode
	private AccountRegistration accountRegistration;

	/*
	//bi-directional many-to-one association to TestResult
	@OneToMany(mappedBy="account")
	private List<TestResult> passedTests;

	//bi-directional many-to-one association to Test
	@OneToMany(mappedBy="account")
	private List<Test> tests;
	 */
    public Account() {
    }

	public Long getIdAccount() {
		return this.idAccount;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdAccount();
	}

	public void setIdAccount(Long idAccount) {
		this.idAccount = idAccount;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFio() {
		return this.fio;
	}

	public void setFio(String name) {
		this.fio = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getUpdated() {
		return this.updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public List<AccountRole> getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(List<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}
	
	public AccountRegistration getAccountRegistration() {
		return this.accountRegistration;
	}

	public void setAccountRegistration(AccountRegistration accountRegistration) {
		this.accountRegistration = accountRegistration;
	}
	
	/*public List<TestResult> getPassedTests() {
		return this.passedTests;
	}

	public void setPassedTests(List<TestResult> passedTests) {
		this.passedTests = passedTests;
	}
	
	public List<Test> getTests() {
		return this.tests;
	}

	public void setTests(List<Test> tests) {
		this.tests = tests;
	}*/
	
}