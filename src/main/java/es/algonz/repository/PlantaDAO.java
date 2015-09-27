package es.algonz.repository;

import java.util.List;

import es.algonz.domain.PlantaVO;




public interface PlantaDAO {


	public void persist(PlantaVO transientInstance) ;

	public void remove(PlantaVO persistentInstance);

	public PlantaVO merge(PlantaVO detachedInstance);

	public PlantaVO findById(Integer id);

	public List<PlantaVO> getPlantas(PlantaVO object);
	
}
