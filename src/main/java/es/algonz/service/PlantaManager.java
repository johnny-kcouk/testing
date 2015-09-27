package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.PlantaVO;



public interface PlantaManager extends Serializable {


	public void persist(PlantaVO transientInstance) ;

	public void remove(PlantaVO persistentInstance);

	public PlantaVO merge(PlantaVO detachedInstance);

	public PlantaVO findById(Integer id);

	public List<PlantaVO> getPlantas(PlantaVO object);
	
}
