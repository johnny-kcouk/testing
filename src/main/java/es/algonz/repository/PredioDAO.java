package es.algonz.repository;

import java.util.List;

import es.algonz.domain.PredioVO;



public interface PredioDAO {


	public void persist(PredioVO transientInstance) ;

	public void remove(PredioVO persistentInstance);

	public PredioVO merge(PredioVO detachedInstance);

	public PredioVO findById(Integer id);

	public List<PredioVO> getPredios(PredioVO object);
	
}
