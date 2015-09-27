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

import es.algonz.domain.PlantaVO;

/**
 * DAO Planta.
 * @see es.algonz.PlantaVO
 * 
 */
@Repository(value = "PlantaDAO")
public class PlantaDAOImpl implements PlantaDAO{

	private static final Log LOGGER = LogFactory.getLog(PlantaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PlantaVO transientInstance) {
		LOGGER.debug("persisting Planta instance");
		try {
			entityManager.persist(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(PlantaVO persistentInstance) {
		LOGGER.debug("removing Planta instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PlantaVO merge(PlantaVO detachedInstance) {
		LOGGER.debug("merging Planta instance");
		try {
			PlantaVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public PlantaVO findById(Integer id) {
		LOGGER.debug("getting Planta instance with id: " + id);
		try {
			PlantaVO instance = entityManager.find(PlantaVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<PlantaVO> getPlantas(PlantaVO object) {
		LOGGER.debug("getting Planta list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlantaVO> cq = cb.createQuery(PlantaVO.class);
		Root<PlantaVO> root = cq.from(PlantaVO.class);
		cq.select(root);
		if(object != null && object.getCnPlanta() != null)
			cq.where(cb.equal(root.get("cnPlanta"), object.getCnPlanta()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
