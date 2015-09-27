package es.algonz.repository;

import java.util.List;

import es.algonz.domain.EmpresaVO;



public interface EmpresaDAO {


	public void persist(EmpresaVO transientInstance) ;

	public void remove(EmpresaVO persistentInstance);

	public EmpresaVO merge(EmpresaVO detachedInstance);

	public EmpresaVO findById(Integer id);

	public List<EmpresaVO> getEmpresas(EmpresaVO object);
	
}
