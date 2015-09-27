package es.algonz.repository;

import es.algonz.domain.TerceroVO;



public interface TerceroDAO {


	public void persist(TerceroVO transientInstance) ;

	public void remove(TerceroVO persistentInstance);

	public TerceroVO merge(TerceroVO detachedInstance);

	public TerceroVO findById(Integer id);
	
}
