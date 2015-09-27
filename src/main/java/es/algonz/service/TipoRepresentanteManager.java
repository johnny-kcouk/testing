package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.TipoRepresentanteVO;



public interface TipoRepresentanteManager extends Serializable {


	public void persist(TipoRepresentanteVO transientInstance) ;

	public void remove(TipoRepresentanteVO persistentInstance);

	public TipoRepresentanteVO merge(TipoRepresentanteVO detachedInstance);

	public TipoRepresentanteVO findById(Integer id);

	public List<TipoRepresentanteVO> getTipoRepresentantes(TipoRepresentanteVO object);
	
}
