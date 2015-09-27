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

import es.algonz.domain.DocumentoVO;

/**
 * DAO Documento.
 * @see es.algonz.DocumentoVO
 * 
 */
@Repository(value = "DocumentoDAO")
public class DocumentoDAOImpl implements DocumentoDAO{

	private static final Log LOGGER = LogFactory.getLog(DocumentoDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(DocumentoVO transientInstance) {
		LOGGER.debug("persisting Documento instance");
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
	public void remove(DocumentoVO persistentInstance) {
		LOGGER.debug("removing Documento instance");
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
	public DocumentoVO merge(DocumentoVO detachedInstance) {
		LOGGER.debug("merging Documento instance");
		try {
			DocumentoVO result = entityManager.merge(detachedInstance);
			LOGGER.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			LOGGER.error("merge failed", re);
			throw re;
		}
	}

	public DocumentoVO findById(Integer id) {
		LOGGER.debug("getting Documento instance with id: " + id);
		try {
			DocumentoVO instance = entityManager.find(DocumentoVO.class, id);
			LOGGER.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			LOGGER.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<DocumentoVO> getDocumentos(DocumentoVO object) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<DocumentoVO> cq = cb.createQuery(DocumentoVO.class);
		Root<DocumentoVO> root = cq.from(DocumentoVO.class);
		cq.select(root);
		if(object != null && object.getCnDocumento() != null)
			cq.where(cb.equal(root.get("cnDocumento"), object.getCnDocumento()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
