package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PredioVO;
import es.algonz.repository.ComunidadDAO;

@Service("comunidadManager")
public class ComunidadManagerImpl implements ComunidadManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private ComunidadDAO comunidadDAO;


	@Override
	public void persist(ComunidadVO transientInstance) {
		comunidadDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(ComunidadVO persistentInstance) {
		comunidadDAO.remove(persistentInstance);
		
	}

	@Override
	public ComunidadVO merge(ComunidadVO detachedInstance) {
		return comunidadDAO.merge(detachedInstance);
	}

	@Override
	public ComunidadVO findById(Integer id) {
		return comunidadDAO.findById(id);
	}

	@Override
	public List<ComunidadVO> getComunidades(ComunidadVO object) {
		return comunidadDAO.getComunidades(object);
	}

	@Override
	public List<PredioVO> getRepresentantes(Integer cnComunidad) {
		return comunidadDAO.getRepresentantes(cnComunidad);
	}
}
