package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.TipoEmpresaVO;
import es.algonz.repository.TipoEmpresaDAO;

@Service("tipoEmpresaManager")
public class TipoEmpresaManagerImpl implements TipoEmpresaManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private TipoEmpresaDAO tipoEmpresaDAO;


	@Override
	public void persist(TipoEmpresaVO transientInstance) {
		tipoEmpresaDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(TipoEmpresaVO persistentInstance) {
		tipoEmpresaDAO.remove(persistentInstance);
		
	}

	@Override
	public TipoEmpresaVO merge(TipoEmpresaVO detachedInstance) {
		return tipoEmpresaDAO.merge(detachedInstance);
	}

	@Override
	public TipoEmpresaVO findById(Integer id) {
		return tipoEmpresaDAO.findById(id);
	}

	@Override
	public List<TipoEmpresaVO> getTipoEmpresas(TipoEmpresaVO object) {
		return tipoEmpresaDAO.getTipoEmpresas(object);
	}
}
