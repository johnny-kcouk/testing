package es.algonz.service;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartRequest;

import es.algonz.domain.DocumentoVO;
import es.algonz.domain.FileMeta;



public interface DocumentoManager extends Serializable {


	public void persist(DocumentoVO transientInstance) ;

	public void remove(DocumentoVO persistentInstance);

	public DocumentoVO merge(DocumentoVO detachedInstance);

	public DocumentoVO findById(Integer id);

	public List<DocumentoVO> getDocumentos(DocumentoVO object);
	
	public LinkedList<FileMeta> uploadDocument (MultipartRequest request, DocumentoVO documento);

	public void downloadDocument(HttpServletResponse response, String idDocumento);

	public void deleteDocument(String idDocumento);

//
//	public LinkedList<FileMeta> uploadDocumentComunidad (MultipartRequest request, ComunidadVO comunidad);
//
//	public LinkedList<FileMeta> uploadDocumentActuacion(MultipartRequest request, ActuacionVO actuacion);
//
//	public LinkedList<FileMeta> uploadDocumentAvisoEmpresa(MultipartRequest request, AvisoEmpresaVO avisoEmpresa);
	
}
