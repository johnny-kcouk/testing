package es.algonz.repository;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.PredioVO;

/**
 * DAO Predio.
 * @see es.algonz.PredioVO
 * 
 */
@Repository(value = "PredioDAO")
public class PredioDAOImpl implements PredioDAO {

	private static final Log LOGGER = LogFactory.getLog(PredioDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PredioVO transientInstance) {
		LOGGER.debug("persisting Predio instance");
		try {
			entityManager.persist(transientInstance);
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}
	
	@Transactional
	public void remove(PredioVO persistentInstance) {
		LOGGER.debug("removing Predio instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PredioVO merge(PredioVO detachedInstance) {
		LOGGER.debug("merging Predio instance");
		try {
			PredioVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public PredioVO findById(Integer id) {
		LOGGER.debug("getting Predio instance with id: " + id);
		try {
			PredioVO instance = entityManager.find(PredioVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<PredioVO> getPredios(PredioVO object) {
		LOGGER.debug("getting Predio list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PredioVO> cq = cb.createQuery(PredioVO.class);
		Root<PredioVO> root = cq.from(PredioVO.class);
		cq.select(root);
		
		if (object != null){

			List<Predicate> andPredicates = new ArrayList<Predicate>();
			if(object.getCnPredio() != null)
				andPredicates.add(cb.equal(root.get("cnPredio"), object.getCnPredio()));
			

			if(!StringUtils.isEmpty(object.getTePredio()))
				andPredicates.add(cb.equal(root.get("tePredio"), object.getTePredio()));


			if(object.getPlanta() != null && object.getPlanta().getCnPlanta() != null )
				andPredicates.add(cb.equal(root.get("planta").get("cnPlanta"), object.getPlanta().getCnPlanta()));



			if(object.getPlanta() != null && !StringUtils.isEmpty(object.getPlanta().getTePlanta()))
				andPredicates.add(cb.equal(root.get("planta").get("tePlanta"), object.getPlanta().getTePlanta()));



			if(object.getPortal() != null && object.getPortal().getCnPortal() != null )
				andPredicates.add(cb.equal(root.get("portal").get("cnPortal"), object.getPortal().getCnPortal()));

			
			if(object.getPortal() != null && !StringUtils.isEmpty(object.getPortal().getTeNombre()))
				andPredicates.add(cb.equal(root.get("portal").get("teNombre"), object.getPortal().getTeNombre()));

			
			if (andPredicates.size() > 0)
				cq.where(andPredicates.toArray(new Predicate[andPredicates.size()]));
		
		}
			
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
