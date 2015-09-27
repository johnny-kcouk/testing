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

import es.algonz.domain.UsuarioVO;

@Repository(value = "usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO
{

	private static final Log LOGGER = LogFactory
			.getLog(UsuarioDAOImpl.class);
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UsuarioVO getUsuarioByIdSistema(String idSistema)
	{
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioVO> cq = cb.createQuery(UsuarioVO.class);
		Root<UsuarioVO> root = cq.from(UsuarioVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("idSistema"), idSistema));
		return entityManager.createQuery(cq).getSingleResult();
	}

	@Override
	public List<UsuarioVO> getUsuarios(UsuarioVO usuarioVO) {
		LOGGER.debug("getting TipoRepresentante list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UsuarioVO> cq = cb.createQuery(UsuarioVO.class);
		Root<UsuarioVO> root = cq.from(UsuarioVO.class);
		cq.select(root);
		
		
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public UsuarioVO findById(int id) {
		LOGGER.debug("getting Siniestro instance with id: " + id);
		try {
			UsuarioVO instance = entityManager.find(UsuarioVO.class, new Integer (id).toString());
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Transactional
	public UsuarioVO merge(UsuarioVO detachedInstance) {
		LOGGER.debug("merging Siniestro instance");
		try {
			UsuarioVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	@Transactional
	public void persist(UsuarioVO transientInstance) {
		LOGGER.debug("persisting Siniestro instance");
		try {
			// entityManager.persist(transientInstance);
			
			UsuarioVO result = entityManager.merge(transientInstance);
			
			
			String query="INSERT INTO usuario_rol VALUES ("+ result.getIdUsuario()  +",2); ";
			entityManager.createNativeQuery(query).executeUpdate();
			
			
			
			LOGGER.debug("persist successful");
		} catch (RuntimeException re) {
			LOGGER.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(UsuarioVO persistentInstance) {
		LOGGER.debug("removing Siniestro instance");
		try {
			entityManager.remove(persistentInstance);
			LOGGER.debug("remove successful");
		} catch (RuntimeException re) {
			LOGGER.error("remove failed", re);
			throw re;
		}
		
	}
	
}
