package es.algonz.repository;

import java.util.List;

import es.algonz.domain.UsuarioVO;

public interface UsuarioDAO {

	
	public UsuarioVO getUsuarioByIdSistema(String idSistema);

	public List<UsuarioVO> getUsuarios(UsuarioVO usuarioVO);

	public UsuarioVO findById(int id);

	public UsuarioVO merge(UsuarioVO detachedInstance);

	public void persist(UsuarioVO transientInstance);

	public void remove(UsuarioVO persistentInstance);
	
}
