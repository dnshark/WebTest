package nedis.study.jee.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
public class Role extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ROLE_IDROLE_GENERATOR", sequenceName="role_id_role_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY, generator="ROLE_IDROLE_GENERATOR")
	@Column(name="id_role", unique=true, nullable=false)
	private Integer idRole;

	@Column(nullable=false, length=15)
	private String name;

	/*
	//bi-directional many-to-one association to AccountRole
	@OneToMany(mappedBy="role")
	private List<AccountRole> accountRoles;
	*/

    public Role() {
    }

	public Integer getIdRole() {
		return this.idRole;
	}
	
	@Override
	@Transient
	public Serializable getId() {
		return getIdRole();
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/*public List<AccountRole> getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(List<AccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}*/

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null ) return false;

		if (toString().equals(o.toString())) return true;

		return false;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (idRole != null ? idRole.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Role{" +
				"idRole=" + idRole +
				", name='" + name + '\'' +
				'}';
	}

}