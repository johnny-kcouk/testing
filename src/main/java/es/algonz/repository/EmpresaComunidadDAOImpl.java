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

import es.algonz.domain.EmpresaComunidadVO;

/**
 * DAO EmpresaComunidad.
 * @see es.algonz.EmpresaComunidadVO
 * 
 */
@Repository(value = "EmpresaComunidadDAO")
public class EmpresaComunidadDAOImpl implements EmpresaComunidadDAO{

	private static final Log LOGGER = LogFactory
			.getLog(EmpresaComunidadDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EmpresaComunidadVO transientInstance) {
		LOGGER.debug("persisting EmpresaComunidad instance");
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
	public void remove(EmpresaComunidadVO persistentInstance) {
		LOGGER.debug("removing EmpresaComunidad instance");
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
	public EmpresaComunidadVO merge(EmpresaComunidadVO detachedInstance) {
		LOGGER.debug("merging EmpresaComunidad instance");
		try {
			EmpresaComunidadVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public EmpresaComunidadVO findById(Integer id) {
		LOGGER.debug("getting EmpresaComunidad instance with id: " + id);
		try {
			EmpresaComunidadVO instance = entityManager.find(
					EmpresaComunidadVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<EmpresaComunidadVO> getEmpresasComunidad(
			EmpresaComunidadVO object) {
		LOGGER.debug("getting EmpresaComunidad list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpresaComunidadVO> cq = cb.createQuery(EmpresaComunidadVO.class);
		Root<EmpresaComunidadVO> root = cq.from(EmpresaComunidadVO.class);
		cq.select(root);
		if(object != null && object.getCnEmpresaComunidad() != null)
			cq.where(cb.equal(root.get("cnEmpresaComunidad"), object.getCnEmpresaComunidad()));
		else if (object != null && object.getComunidad() != null && object.getComunidad().getCnComunidad() != null && object.getEmpresa() != null && object.getEmpresa().getTipoEmpresa() != null 
				&& object.getEmpresa().getTipoEmpresa().getCnTipoEmpresa() != null){

			cq.where(  cb.and( cb.equal(root.get("empresa").get("tipoEmpresa"), object.getEmpresa().getTipoEmpresa()), 
					           cb.equal(root.get("comunidad"), object.getComunidad())  
					           )   
					 );
		}
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
