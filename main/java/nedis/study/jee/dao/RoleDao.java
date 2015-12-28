package nedis.study.jee.dao;

import nedis.study.jee.entities.Role;

/**
 * @author nedis
 * @version 1.0
 */
public interface RoleDao extends IEntityDao<Role> {

    Role getStudentRole();

    Role getRole(int idRole);
}
