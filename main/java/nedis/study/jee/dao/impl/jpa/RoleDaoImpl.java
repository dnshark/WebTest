package nedis.study.jee.dao.impl.jpa;

import org.springframework.stereotype.Repository;

import nedis.study.jee.dao.RoleDao;
import nedis.study.jee.entities.Role;

/**
 * @author nedis
 * @version 1.0
 */
@Repository("jpaRoleDao")
public class RoleDaoImpl extends AbstractEntityDao<Role> implements RoleDao {

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
	public Role getStudentRole() {
		return (Role) em.createQuery("from Role r where r.idRole = :idRole").setParameter("idRole", 4L).getSingleResult();
	}
}
