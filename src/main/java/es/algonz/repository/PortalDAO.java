package es.algonz.repository;

import java.util.List;

import es.algonz.domain.PortalVO;



public interface PortalDAO {


	public void persist(PortalVO transientInstance) ;

	public void remove(PortalVO persistentInstance);

	public PortalVO merge(PortalVO detachedInstance);

	public PortalVO findById(Integer id);

	public List<PortalVO> getPortales(PortalVO object);
	
}
