package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.SiniestroVO;
import es.algonz.repository.SiniestroDAO;

@Service("siniestroManager")
public class SiniestroManagerImpl implements SiniestroManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private SiniestroDAO siniestroDAO;


	@Override
	public void persist(SiniestroVO transientInstance) {
		siniestroDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(SiniestroVO persistentInstance) {
		siniestroDAO.remove(persistentInstance);
		
	}

	@Override
	public SiniestroVO merge(SiniestroVO detachedInstance) {
		return siniestroDAO.merge(detachedInstance);
	}

	@Override
	public SiniestroVO findById(Integer id) {
		return siniestroDAO.findById(id);
	}

	@Override
	public List<SiniestroVO> getSiniestros(SiniestroVO object) {
		return siniestroDAO.getSiniestros(object);
	}
}
