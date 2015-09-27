		package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.AvisoEmpresaVO;



public interface AvisoEmpresaManager extends Serializable {


	public void persist(AvisoEmpresaVO transientInstance) ;

	public void remove(AvisoEmpresaVO persistentInstance);

	public AvisoEmpresaVO merge(AvisoEmpresaVO detachedInstance);

	public AvisoEmpresaVO findById(Integer id);

	public List<AvisoEmpresaVO> getAvisosEmpresa(AvisoEmpresaVO object);

	public List<AvisoEmpresaVO> getAvisosEmpresaProximoVencimiento();
	
	public List<AvisoEmpresaVO> getAvisosEmpresaUsuarioProximoVencimiento(String idUsuario);

	public List<AvisoEmpresaVO> getAvisosEmpresaAbiertas(Integer cnEmpresa);

	
}
