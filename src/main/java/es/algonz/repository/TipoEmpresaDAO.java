package es.algonz.repository;

import java.util.List;

import es.algonz.domain.TipoEmpresaVO;



public interface TipoEmpresaDAO {


	public void persist(TipoEmpresaVO transientInstance) ;

	public void remove(TipoEmpresaVO persistentInstance);

	public TipoEmpresaVO merge(TipoEmpresaVO detachedInstance);

	public TipoEmpresaVO findById(Integer id);

	public List<TipoEmpresaVO> getTipoEmpresas(TipoEmpresaVO object);
	
}
