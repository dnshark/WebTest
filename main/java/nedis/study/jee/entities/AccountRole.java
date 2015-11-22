package nedis.study.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the account_role database table.
 * 
 */
@Entity
@Table(name="account_role")
public class AccountRole extends AbstractEntity  {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_ROLE_IDACCOUNTROLE_GENERATOR", sequenceName="account_role_id_account_role_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ACCOUNT_ROLE_IDACCOUNTROLE_GENERATOR")
	@Column(name="id_account_role", unique=true, nullable=false)
	private Long idAccountRole;

	//bi-directional many-to-one association to Account
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_account", nullable=false)
	private Account account;

	//bi-directional many-to-one association to Role
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="id_role", nullable=false)
	private Role role;

    public AccountRole() {
    	
    }

	public AccountRole(Account account, Role role) {
		super();
		this.account = account;
		this.role = role;
	}

	public Long getIdAccountRole() {
		return this.idAccountRole;
	}
	
	@Transient
	@Override
	public Serializable getId() {
		return getIdAccountRole();
	}

	public void setIdAccountRole(Long idAccountRole) {
		this.idAccountRole = idAccountRole;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public Role getRole() {
		return this.role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}