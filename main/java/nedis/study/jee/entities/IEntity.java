package nedis.study.jee.entities;

import java.io.Serializable;

/**
 * @author nedis
 * @version 1.0
 */
public interface IEntity extends Serializable {
	
	Serializable getId();
}
