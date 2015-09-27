package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.EmpresaComunidadVO;
import es.algonz.repository.EmpresaComunidadDAO;

@Service("empresaComunidadManager")
public class EmpresaComunidadManagerImpl implements EmpresaComunidadManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private EmpresaComunidadDAO empresaComunidadDAO;


	@Override
	public void persist(EmpresaComunidadVO transientInstance) {
		empresaComunidadDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(EmpresaComunidadVO persistentInstance) {
		empresaComunidadDAO.remove(persistentInstance);
		
	}

	@Override
	public EmpresaComunidadVO merge(EmpresaComunidadVO detachedInstance) {
		return empresaComunidadDAO.merge(detachedInstance);
	}

	@Override
	public EmpresaComunidadVO findById(Integer id) {
		return empresaComunidadDAO.findById(id);
	}

	@Override
	public List<EmpresaComunidadVO> getEmpresasComunidad(EmpresaComunidadVO object) {
		return empresaComunidadDAO.getEmpresasComunidad(object);
	}
}
