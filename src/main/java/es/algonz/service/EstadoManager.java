package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.EstadoVO;



public interface EstadoManager extends Serializable {


	public void persist(EstadoVO transientInstance) ;

	public void remove(EstadoVO persistentInstance);

	public EstadoVO merge(EstadoVO detachedInstance);

	public EstadoVO findById(Integer id);

	public List<EstadoVO> getEstados(EstadoVO object);
	
}
