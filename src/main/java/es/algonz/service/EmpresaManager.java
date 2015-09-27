package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.EmpresaVO;



public interface EmpresaManager extends Serializable {


	public void persist(EmpresaVO transientInstance) ;

	public void remove(EmpresaVO persistentInstance);

	public EmpresaVO merge(EmpresaVO detachedInstance);

	public EmpresaVO findById(Integer id);

	public List<EmpresaVO> getEmpresas(EmpresaVO object);
	
}
