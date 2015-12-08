package nedis.study.jee.dao.impl.hibernate;

import nedis.study.jee.ApplicationConstants;
import nedis.study.jee.dao.RoleDao;
import nedis.study.jee.entities.Role;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * @author nedis
 * @version 1.0
 */
@Repository("hibernateRoleDao")
public class RoleDaoImpl extends AbstractEntityDao<Role> implements RoleDao,ApplicationConstants {

	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

	@Override
		 public Role getStudentRole() {
		return getRole(STUDENT_ROLE);
	}

	@Override
	public Role getRole(int idRole) {
		return (Role) getSession().createCriteria(getEntityClass()).add(Restrictions.eq("idRole", idRole)).uniqueResult();
	}

}
