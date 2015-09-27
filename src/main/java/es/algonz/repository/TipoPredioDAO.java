package es.algonz.repository;

import es.algonz.domain.TipoPredioVO;



public interface TipoPredioDAO {


	public void persist(TipoPredioVO transientInstance) ;

	public void remove(TipoPredioVO persistentInstance);

	public TipoPredioVO merge(TipoPredioVO detachedInstance);

	public TipoPredioVO findById(Integer id);
	
}
