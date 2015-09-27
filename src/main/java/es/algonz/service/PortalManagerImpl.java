package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.PortalVO;
import es.algonz.repository.PortalDAO;

@Service("portalManager")
public class PortalManagerImpl implements PortalManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private PortalDAO portalDAO;


	@Override
	public void persist(PortalVO transientInstance) {
		portalDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(PortalVO persistentInstance) {
		portalDAO.remove(persistentInstance);
		
	}

	@Override
	public PortalVO merge(PortalVO detachedInstance) {
		return portalDAO.merge(detachedInstance);
	}

	@Override
	public PortalVO findById(Integer id) {
		return portalDAO.findById(id);
	}

	@Override
	public List<PortalVO> getPortales(PortalVO object) {
		return portalDAO.getPortales(object);
	}
}
