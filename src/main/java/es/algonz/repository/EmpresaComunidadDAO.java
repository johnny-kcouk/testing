package es.algonz.repository;

import java.util.List;

import es.algonz.domain.EmpresaComunidadVO;



public interface EmpresaComunidadDAO {


	public void persist(EmpresaComunidadVO transientInstance) ;

	public void remove(EmpresaComunidadVO persistentInstance);

	public EmpresaComunidadVO merge(EmpresaComunidadVO detachedInstance);

	public EmpresaComunidadVO findById(Integer id);

	public List<EmpresaComunidadVO> getEmpresasComunidad(
			EmpresaComunidadVO object);
	
}
