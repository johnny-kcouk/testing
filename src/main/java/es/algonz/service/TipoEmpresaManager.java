package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.TipoEmpresaVO;



public interface TipoEmpresaManager extends Serializable {


	public void persist(TipoEmpresaVO transientInstance) ;

	public void remove(TipoEmpresaVO persistentInstance);

	public TipoEmpresaVO merge(TipoEmpresaVO detachedInstance);

	public TipoEmpresaVO findById(Integer id);

	public List<TipoEmpresaVO> getTipoEmpresas(TipoEmpresaVO object);
	
}
