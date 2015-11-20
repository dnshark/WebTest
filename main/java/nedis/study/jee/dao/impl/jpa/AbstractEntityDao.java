package nedis.study.jee.dao.impl.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import nedis.study.jee.dao.IEntityDao;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author nedis
 * @version 1.0
 */
@SuppressWarnings("unchecked")
public abstract class AbstractEntityDao<T> implements IEntityDao<T> {
	protected final Logger LOGGER = Logger.getLogger(getClass());
	
	@Autowired
	protected EntityManager em;
	
	protected abstract Class<T> getEntityClass();
	
	@Override
	public void save(final T e) {
		em.persist(e);
	}

	@Override
	public void update(final T e) {
		em.merge(e);
	}

	@Override
	public void delete(final T e) {
		em.remove(e);
	}

	@Override
	public void remove(final T e) {
		delete(e);
	}

	
	@Override
	public T findById(final Serializable id) {
		return em.find(getEntityClass(), id);
	}

	@Override
	public List<T> findAll() {
		return em.createQuery("from "+getEntityClass().getName()).getResultList();
	}
	
	@Override
	public void close() {
		em.close();
	}
}
