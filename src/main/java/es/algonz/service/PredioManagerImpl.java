package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.PredioVO;
import es.algonz.repository.PredioDAO;

@Service("predioManager")
public class PredioManagerImpl implements PredioManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private PredioDAO predioDAO;


	@Override
	public void persist(PredioVO transientInstance) {
		predioDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(PredioVO persistentInstance) {
		predioDAO.remove(persistentInstance);
		
	}

	@Override
	public PredioVO merge(PredioVO detachedInstance) {
		return predioDAO.merge(detachedInstance);
	}

	@Override
	public PredioVO findById(Integer id) {
		return predioDAO.findById(id);
	}

	@Override
	public List<PredioVO> getPredios(PredioVO object) {
		return predioDAO.getPredios(object);
	}
}
