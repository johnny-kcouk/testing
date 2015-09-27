package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PredioVO;



public interface ComunidadManager extends Serializable {


	public void persist(ComunidadVO transientInstance) ;

	public void remove(ComunidadVO persistentInstance);

	public ComunidadVO merge(ComunidadVO detachedInstance);

	public ComunidadVO findById(Integer id);

	public List<ComunidadVO> getComunidades(ComunidadVO object);

	public List<PredioVO> getRepresentantes(Integer cnComunidad);
	
}
