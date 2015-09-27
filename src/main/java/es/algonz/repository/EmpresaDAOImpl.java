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

import es.algonz.domain.EmpresaVO;

/**
 * DAO Empresa.
 * @see es.algonz.EmpresaVO
 * 
 */
@Repository(value = "EmpresaDAO")
public class EmpresaDAOImpl implements EmpresaDAO{

	private static final Log LOGGER = LogFactory.getLog(EmpresaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EmpresaVO transientInstance) {
		LOGGER.debug("persisting Empresa instance");
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
	public void remove(EmpresaVO persistentInstance) {
		LOGGER.debug("removing Empresa instance");
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
	public EmpresaVO merge(EmpresaVO detachedInstance) {
		LOGGER.debug("merging Empresa instance");
		try {
			EmpresaVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public EmpresaVO findById(Integer id) {
		LOGGER.debug("getting Empresa instance with id: " + id);
		try {
			EmpresaVO instance = entityManager.find(EmpresaVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<EmpresaVO> getEmpresas(EmpresaVO object) {
		LOGGER.debug("getting Empresa list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpresaVO> cq = cb.createQuery(EmpresaVO.class);
		Root<EmpresaVO> root = cq.from(EmpresaVO.class);
		cq.select(root);
		if(object != null && object.getCnEmpresa() != null)
			cq.where(cb.equal(root.get("cnEmpresa"), object.getCnEmpresa()));
		else if(object != null && object.getTipoEmpresa() != null && object.getTipoEmpresa().getCnTipoEmpresa() != null)
			cq.where(cb.equal(root.get("tipoEmpresa"), object.getTipoEmpresa()));
		
		cq.orderBy(cb.asc(root.get("tipoEmpresa")), cb.asc(root.get("teNombre")));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
