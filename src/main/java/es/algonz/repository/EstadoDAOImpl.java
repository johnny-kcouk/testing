package es.algonz.repository;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.EstadoVO;

/**
 * DAO Estado.
 * @see es.algonz.EstadoVO
 * 
 */
@Repository(value = "EstadoDAO")
public class EstadoDAOImpl implements EstadoDAO {

	private static final Log LOGGER = LogFactory.getLog(EstadoDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EstadoVO transientInstance) {
		LOGGER.debug("persisting Estado instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(EstadoVO persistentInstance) {
		LOGGER.debug("removing Estado instance");
		try {
			entityManager.remove(persistentInstance);
			entityManager.flush();
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public EstadoVO merge(EstadoVO detachedInstance) {
		LOGGER.debug("merging Estado instance");
		try {
			EstadoVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public EstadoVO findById(Integer id) {
		LOGGER.debug("getting Estado instance with id: " + id);
		try {
			EstadoVO instance = entityManager.find(EstadoVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<EstadoVO> getEstados(EstadoVO object) {
		LOGGER.debug("getting Estado list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EstadoVO> cq = cb.createQuery(EstadoVO.class);
		Root<EstadoVO> root = cq.from(EstadoVO.class);
		cq.select(root);
		if(object != null && object.getCnEstado() != null)
			cq.where(cb.equal(root.get("cnEstado"), object.getCnEstado()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
