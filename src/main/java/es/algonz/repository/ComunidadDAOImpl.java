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

import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PredioVO;

/**
 * DAO Comunidad.
 * @see es.algonz.ComunidadVO
 * 
 */
@Repository(value = "ComunidadDAO")
public class ComunidadDAOImpl implements ComunidadDAO {

	private static final Log LOGGER = LogFactory.getLog(ComunidadDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(ComunidadVO transientInstance) {
		LOGGER.debug("persisting Comunidad instance");
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
	public void remove(ComunidadVO persistentInstance) {
		LOGGER.debug("removing Comunidad instance");
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
	public ComunidadVO merge(ComunidadVO detachedInstance) {
		LOGGER.debug("merging Comunidad instance");
		try {
			ComunidadVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public ComunidadVO findById(Integer id) {
		LOGGER.debug("getting Comunidad instance with id: " + id);
		try {
			ComunidadVO instance = entityManager.find(ComunidadVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ComunidadVO> getComunidades(ComunidadVO object) {
		LOGGER.debug("getting Comunidad list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ComunidadVO> cq = cb.createQuery(ComunidadVO.class);
		Root<ComunidadVO> root = cq.from(ComunidadVO.class);
		cq.select(root);
		if(object != null && object.getCnComunidad() != null)
			cq.where(cb.equal(root.get("cnComunidad"), object.getCnComunidad()));
		
		if(object != null && object.getGestor() != null  && object.getGestor().getIdUsuario() != null)
			cq.where(cb.equal(root.get("gestor").get("idUsuario"), object.getGestor().getIdUsuario()));
		
			
		cq.orderBy(cb.asc(root.get("teNombre")));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PredioVO> getRepresentantes(Integer cnComunidad) {
	
		String query="SELECT pr.* " +
				"FROM  portal p, predio pr " +
				"WHERE p.cn_Comunidad = " + cnComunidad +
				" AND pr.cn_portal = p.cn_portal " +
				"AND pr.cn_tipo_representante is not null order by pr.cn_tipo_representante asc;";
		
		return entityManager.createNativeQuery(query,PredioVO.class).getResultList();

		
	}
}
