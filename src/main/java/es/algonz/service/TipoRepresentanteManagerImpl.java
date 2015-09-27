package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.TipoRepresentanteVO;
import es.algonz.repository.TipoRepresentanteDAO;

@Service("tipoRepresentanteManager")
public class TipoRepresentanteManagerImpl implements TipoRepresentanteManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private TipoRepresentanteDAO tipoRepresentanteDAO;


	@Override
	public void persist(TipoRepresentanteVO transientInstance) {
		tipoRepresentanteDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(TipoRepresentanteVO persistentInstance) {
		tipoRepresentanteDAO.remove(persistentInstance);
		
	}

	@Override
	public TipoRepresentanteVO merge(TipoRepresentanteVO detachedInstance) {
		return tipoRepresentanteDAO.merge(detachedInstance);
	}

	@Override
	public TipoRepresentanteVO findById(Integer id) {
		return tipoRepresentanteDAO.findById(id);
	}

	@Override
	public List<TipoRepresentanteVO> getTipoRepresentantes(TipoRepresentanteVO object) {
		return tipoRepresentanteDAO.getTipoRepresentantes(object);
	}
}
