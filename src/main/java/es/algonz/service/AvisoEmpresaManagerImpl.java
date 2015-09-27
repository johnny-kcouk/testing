package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.repository.AvisoEmpresaDAO;

@Service("avisoEmpresaManager")
public class AvisoEmpresaManagerImpl implements AvisoEmpresaManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private AvisoEmpresaDAO avisoEmpresaDAO;


	@Override
	public void persist(AvisoEmpresaVO transientInstance) {
		avisoEmpresaDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(AvisoEmpresaVO persistentInstance) {
		avisoEmpresaDAO.remove(persistentInstance);
		
	}

	@Override
	public AvisoEmpresaVO merge(AvisoEmpresaVO detachedInstance) {
		return avisoEmpresaDAO.merge(detachedInstance);
	}

	@Override
	public AvisoEmpresaVO findById(Integer id) {
		return avisoEmpresaDAO.findById(id);
	}

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresa(AvisoEmpresaVO object) {
		return avisoEmpresaDAO.getAvisosEmpresa(object);
	}

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresaProximoVencimiento() {
		return avisoEmpresaDAO.getAvisosEmpresaProximoVencimiento();
	}
	
	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresaUsuarioProximoVencimiento(String idUsuario) {
		return avisoEmpresaDAO.getAvisosEmpresaUsuarioProximoVencimiento(idUsuario);
	}

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresaAbiertas(Integer cnEmpresa) {
		return avisoEmpresaDAO.getAvisosEmpresaAbiertas(cnEmpresa);
	}

}
