package es.algonz.repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.TerceroVO;

/**
 * DAO Tercero.
 * @see es.algonz.TerceroVO
 * 
 */
@Repository(value = "TerceroDAO")
public class TerceroDAOImpl implements TerceroDAO{

	private static final Log LOGGER = LogFactory.getLog(TerceroDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(TerceroVO transientInstance) {
		LOGGER.debug("persisting Tercero instance");
		try {
			entityManager.persist(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(TerceroVO persistentInstance) {
		LOGGER.debug("removing Tercero instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public TerceroVO merge(TerceroVO detachedInstance) {
		LOGGER.debug("merging Tercero instance");
		try {
			TerceroVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public TerceroVO findById(Integer id) {
		LOGGER.debug("getting Tercero instance with id: " + id);
		try {
			TerceroVO instance = entityManager.find(TerceroVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}
}
