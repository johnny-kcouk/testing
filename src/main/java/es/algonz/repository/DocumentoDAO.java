package es.algonz.repository;

import java.util.List;

import es.algonz.domain.DocumentoVO;

public interface DocumentoDAO {


	public void persist(DocumentoVO transientInstance) ;

	public void remove(DocumentoVO persistentInstance);

	public DocumentoVO merge(DocumentoVO detachedInstance);

	public DocumentoVO findById(Integer id);

	public List<DocumentoVO> getDocumentos(DocumentoVO object);
	
}
