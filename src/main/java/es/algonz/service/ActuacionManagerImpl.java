package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.ActuacionVO;
import es.algonz.repository.ActuacionDAO;

@Service("actuacionManager")
public class ActuacionManagerImpl implements ActuacionManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private ActuacionDAO actuacionDAO;


	@Override
	public void persist(ActuacionVO transientInstance) {
		actuacionDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(ActuacionVO persistentInstance) {
		actuacionDAO.remove(persistentInstance);
		
	}

	@Override
	public ActuacionVO merge(ActuacionVO detachedInstance) {
		return actuacionDAO.merge(detachedInstance);
	}

	@Override
	public ActuacionVO findById(Integer id) {
		return actuacionDAO.findById(id);
	}

	@Override
	public List<ActuacionVO> getActuaciones(ActuacionVO object) {
		return actuacionDAO.getActuaciones(object);
	}

	@Override
	public List<ActuacionVO> getActuacionesProximoVencimiento() {
		return actuacionDAO.getActuacionesProximoVencimiento();
	}

	@Override
	public List<ActuacionVO> getActuacionesAbiertas(Integer cnEmpresa) {
		return actuacionDAO.getActuacionesAbiertas(cnEmpresa);
	}

	@Override
	public List<ActuacionVO> getActuacionesUsuarioProximoVencimiento(String idUsuario) {
		return actuacionDAO.getActuacionesUsuarioProximoVencimiento(idUsuario);
	}
}
