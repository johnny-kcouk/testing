package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.EstadoVO;
import es.algonz.repository.EstadoDAO;

@Service("estadoManager")
public class EstadoManagerImpl implements EstadoManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private EstadoDAO estadoDAO;


	@Override
	public void persist(EstadoVO transientInstance) {
		estadoDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(EstadoVO persistentInstance) {
		estadoDAO.remove(persistentInstance);
		
	}

	@Override
	public EstadoVO merge(EstadoVO detachedInstance) {
		return estadoDAO.merge(detachedInstance);
	}

	@Override
	public EstadoVO findById(Integer id) {
		return estadoDAO.findById(id);
	}

	@Override
	public List<EstadoVO> getEstados(EstadoVO object) {
		return estadoDAO.getEstados(object);
	}
}
