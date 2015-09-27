package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.UsuarioVO;
import es.algonz.repository.UsuarioDAO;

@Service(value="usuarioManager")
public class UsuarioManagerImpl implements UsuarioManager {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public UsuarioVO getUsuarioByIdSistema(String idSistema) {
		return usuarioDAO.getUsuarioByIdSistema(idSistema);
	}

	@Override
	public List<UsuarioVO> getUsuarios(UsuarioVO object) {
		return usuarioDAO.getUsuarios(object);
	}

	@Override
	public UsuarioVO findById(int id) {
		return usuarioDAO.findById(id);
	}

	@Override
	public UsuarioVO merge(UsuarioVO detachedInstance) {
		return usuarioDAO.merge(detachedInstance);
	}

	@Override
	public void persist(UsuarioVO transientInstance) {
		usuarioDAO.persist(transientInstance);
	}
	

	@Override
	public void remove(UsuarioVO persistentInstance) {
		usuarioDAO.remove(persistentInstance);
		
	}
}
