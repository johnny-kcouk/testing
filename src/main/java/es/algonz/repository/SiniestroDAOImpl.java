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

import es.algonz.domain.SiniestroVO;

/**
 * DAO Siniestro.
 * @see es.algonz.SiniestroVO
 * 
 */
@Repository(value = "SiniestroDAO")
public class SiniestroDAOImpl implements SiniestroDAO {

	private static final Log LOGGER = LogFactory.getLog(SiniestroDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(SiniestroVO transientInstance) {
		LOGGER.debug("persisting Siniestro instance");
		try {
			entityManager.persist(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(SiniestroVO persistentInstance) {
		LOGGER.debug("removing Siniestro instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public SiniestroVO merge(SiniestroVO detachedInstance) {
		LOGGER.debug("merging Siniestro instance");
		try {
			SiniestroVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public SiniestroVO findById(Integer id) {
		LOGGER.debug("getting Siniestro instance with id: " + id);
		try {
			SiniestroVO instance = entityManager.find(SiniestroVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<SiniestroVO> getSiniestros(SiniestroVO object) {
		LOGGER.debug("getting Siniestro list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<SiniestroVO> cq = cb.createQuery(SiniestroVO.class);
		Root<SiniestroVO> root = cq.from(SiniestroVO.class);
		cq.select(root);
		if(object != null && object.getCnSiniestro() != null)
			cq.where(cb.equal(root.get("cnSiniestro"), object.getCnSiniestro()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
