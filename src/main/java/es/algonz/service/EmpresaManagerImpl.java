package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.EmpresaVO;
import es.algonz.repository.EmpresaDAO;

@Service("empresaManager")
public class EmpresaManagerImpl implements EmpresaManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private EmpresaDAO empresaDAO;


	@Override
	public void persist(EmpresaVO transientInstance) {
		empresaDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(EmpresaVO persistentInstance) {
		empresaDAO.remove(persistentInstance);
		
	}

	@Override
	public EmpresaVO merge(EmpresaVO detachedInstance) {
		return empresaDAO.merge(detachedInstance);
	}

	@Override
	public EmpresaVO findById(Integer id) {
		return empresaDAO.findById(id);
	}

	@Override
	public List<EmpresaVO> getEmpresas(EmpresaVO object) {
		return empresaDAO.getEmpresas(object);
	}
}
