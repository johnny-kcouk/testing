package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.PlantaVO;
import es.algonz.repository.PlantaDAO;

@Service("plantaManager")
public class PlantaManagerImpl implements PlantaManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private PlantaDAO plantaDAO;


	@Override
	public void persist(PlantaVO transientInstance) {
		plantaDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(PlantaVO persistentInstance) {
		plantaDAO.remove(persistentInstance);
		
	}

	@Override
	public PlantaVO merge(PlantaVO detachedInstance) {
		return plantaDAO.merge(detachedInstance);
	}

	@Override
	public PlantaVO findById(Integer id) {
		return plantaDAO.findById(id);
	}

	@Override
	public List<PlantaVO> getPlantas(PlantaVO object) {
		return plantaDAO.getPlantas(object);
	}
}
