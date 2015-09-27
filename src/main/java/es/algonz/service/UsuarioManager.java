package es.algonz.service;

import java.util.List;

import es.algonz.domain.UsuarioVO;

public interface UsuarioManager {

	public UsuarioVO getUsuarioByIdSistema(String idSistema);

	public List<UsuarioVO> getUsuarios(UsuarioVO object);

	public UsuarioVO findById(int id);

	public void persist(UsuarioVO transientInstance) ;

	public void remove(UsuarioVO persistentInstance);

	public UsuarioVO merge(UsuarioVO detachedInstance);
	
	
	
}
