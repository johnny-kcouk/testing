package es.algonz.repository;

import java.util.List;

import es.algonz.domain.TipoRepresentanteVO;



public interface TipoRepresentanteDAO {


	public void persist(TipoRepresentanteVO transientInstance) ;

	public void remove(TipoRepresentanteVO persistentInstance);

	public TipoRepresentanteVO merge(TipoRepresentanteVO detachedInstance);

	public TipoRepresentanteVO findById(Integer id);

	public List<TipoRepresentanteVO> getTipoRepresentantes(
			TipoRepresentanteVO object);
	
}
