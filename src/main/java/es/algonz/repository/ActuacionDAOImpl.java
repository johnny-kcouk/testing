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

import es.algonz.domain.ActuacionVO;
import es.algonz.web.utils.ConstantesKeys;

/**
 * DAO Actuacion.
 * @see es.algonz.ActuacionVO
 * 
 */
@Repository(value = "ActuacionDAO")
public class ActuacionDAOImpl implements ActuacionDAO {

	private static final Log LOGGER = LogFactory.getLog(ActuacionDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(ActuacionVO transientInstance) {
		LOGGER.debug("persisting Actuacion instance");
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
	public void remove(ActuacionVO persistentInstance) {
		LOGGER.debug("removing Actuacion instance");
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
	public ActuacionVO merge(ActuacionVO detachedInstance) {
		LOGGER.debug("merging Actuacion instance");
		try {
			ActuacionVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public ActuacionVO findById(Integer id) {
		LOGGER.debug("getting Actuacion instance with id: " + id);
		try {
			ActuacionVO instance = entityManager.find(ActuacionVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ActuacionVO> getActuaciones(ActuacionVO object) {
		LOGGER.debug("getting Actuacion list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActuacionVO> cq = cb.createQuery(ActuacionVO.class);
		Root<ActuacionVO> root = cq.from(ActuacionVO.class);
		cq.select(root);
		if(object != null && object.getCnActuacion() != null)
			cq.where(cb.equal(root.get("cnActuacion"), object.getCnActuacion()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ActuacionVO> getActuacionesProximoVencimiento() {
		// El aviso salta 15 días antes de la fecha de vencimiento
		String query="select * from actuacion " +
				"where fe_vencimiento <= DATE_ADD(CURDATE(),INTERVAL " + ConstantesKeys.DIAS_AVISO_SINIESTRO +" DAY) " +
				"and cn_estado <> 2 " + // Estado CERRADO no se muestran
				"order by fe_vencimiento asc;";
		return entityManager.createNativeQuery(query,ActuacionVO.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActuacionVO> getActuacionesUsuarioProximoVencimiento(String idUsuario) {
		// El aviso salta 15 días antes de la fecha de vencimiento
		String query="select a.* from actuacion a, siniestro s, portal p,  comunidad c " +
				"where fe_vencimiento <= DATE_ADD(CURDATE(),INTERVAL " + ConstantesKeys.DIAS_AVISO_SINIESTRO +" DAY) " +
				"and cn_estado <> 2 " + // Estado CERRADO no se muestran
				"and a.cn_siniestro = s.cn_siniestro " +
				"and s.cn_portal = p.cn_portal  " +
				"and p.cn_comunidad = c.cn_comunidad " +
				"and c.idUsuario = " + idUsuario + " " +
				"order by fe_vencimiento asc;";
		return entityManager.createNativeQuery(query,ActuacionVO.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ActuacionVO> getActuacionesAbiertas(Integer cnEmpresa) {
		String query="select a.* from actuacion a, siniestro s, empresa_comunidad ec " +
				"where  ec.cn_empresa = '" + cnEmpresa + "' " +
				"and a.cn_siniestro = s.cn_siniestro " +
				"and s.cn_empresa_comunidad = ec.cn_empresa_comunidad " +
				"and a.cn_estado <> 2 " + // Estado CERRADO no se muestran
				"order by a.fe_vencimiento asc;";
		return entityManager.createNativeQuery(query,ActuacionVO.class).getResultList();
	}
	
	
	
}
