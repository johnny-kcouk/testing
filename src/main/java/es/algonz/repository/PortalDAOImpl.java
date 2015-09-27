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

import es.algonz.domain.PortalVO;

/**
 * DAO Portal.
 * @see es.algonz.PortalVO
 * 
 */
@Repository(value = "PortalDAO")
public class PortalDAOImpl implements PortalDAO{

	private static final Log LOGGER = LogFactory.getLog(PortalDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PortalVO transientInstance) {
		LOGGER.debug("persisting Portal instance");
		try {
			entityManager.persist(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(PortalVO persistentInstance) {
		LOGGER.debug("removing Portal instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PortalVO merge(PortalVO detachedInstance) {
		LOGGER.debug("merging Portal instance");
		try {
			PortalVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public PortalVO findById(Integer id) {
		LOGGER.debug("getting Portal instance with id: " + id);
		try {
			PortalVO instance = entityManager.find(PortalVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<PortalVO> getPortales(PortalVO object) {
		LOGGER.debug("getting Portal list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PortalVO> cq = cb.createQuery(PortalVO.class);
		Root<PortalVO> root = cq.from(PortalVO.class);
		cq.select(root);
		if(object != null && object.getCnPortal() != null)
			cq.where(cb.equal(root.get("cnPortal"), object.getCnPortal()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
