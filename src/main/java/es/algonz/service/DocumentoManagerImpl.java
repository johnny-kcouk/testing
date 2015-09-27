package es.algonz.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import es.algonz.domain.DocumentoVO;
import es.algonz.domain.FileMeta;
import es.algonz.repository.DocumentoDAO;

@Service("documentoManager")
public class DocumentoManagerImpl implements DocumentoManager {

	private static final long serialVersionUID = 1913666680422746714L;
	
	private static final Log LOGGER = LogFactory.getLog(DocumentoManagerImpl.class);

	
	@Value( "${documentPath}" )
	private String documentPath;
	
	@Autowired
	private DocumentoDAO documentoDAO;


	@Override
	public void persist(DocumentoVO transientInstance) {
		documentoDAO.persist(transientInstance);
		
	}

	@Override
	public void remove(DocumentoVO persistentInstance) {
		documentoDAO.remove(persistentInstance);
		
	}

	@Override
	public DocumentoVO merge(DocumentoVO detachedInstance) {
		return documentoDAO.merge(detachedInstance);
	}

	@Override
	public DocumentoVO findById(Integer id) {
		return documentoDAO.findById(id);
	}

	@Override
	public List<DocumentoVO> getDocumentos(DocumentoVO object) {
		return documentoDAO.getDocumentos(object);
	}

	@Override
	public LinkedList<FileMeta> uploadDocument(MultipartRequest request,
			DocumentoVO documento) {
		
		
		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
		
		// 1. build an iterator
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;

		// 2. get each file
		while (itr.hasNext()) {

			// 2.1 get next MultipartFile
			mpf = request.getFile(itr.next());

			
			try {
				FileMeta fileMeta = new FileMeta();
				fileMeta.setFileName(mpf.getOriginalFilename());
				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
				fileMeta.setFileType(mpf.getContentType());

				try {
					fileMeta.setBytes(mpf.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					LOGGER.error(e.getMessage(),e);
				}
				
				
				
				OutputStream outputStream = null;
				InputStream inputStream = null;
				
				
				String path = documentPath;
				
				
				
				// Creamos la estructura de directorios
				new File (path).mkdirs();
				
				String fullPath = path + "/"
						+ mpf.getOriginalFilename() + "_" + new Date().getTime();
				outputStream = new FileOutputStream(fullPath);

				inputStream = mpf.getInputStream();

				int readBytes = 0;
				byte[] buffer = new byte[8192];

				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();

				
				documento.setFeGuardado(new Date());
				documento.setTeNombre(mpf.getOriginalFilename());
				documento.setTePath(fullPath);
				documento.setFileType(mpf.getContentType());
				documento.setFileSize( new Long(mpf.getSize() / 1024).intValue()); // Kb
				documentoDAO.persist(documento);
				
				ficherosSubidos.add(fileMeta);
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(),e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(),e);
			}

		}
		// result will be like this
		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
		return ficherosSubidos;
		
	}

	@Override
	public void downloadDocument(HttpServletResponse response, String idDocumento) {
		try {
			DocumentoVO documento = documentoDAO.findById(new Integer(idDocumento));
			
			File file = new File(documento.getTePath());
			
			FileInputStream fis = new FileInputStream(file);
				response.setContentType(documento.getFileType());
				response.setHeader("Content-disposition", "attachment; filename=\""
						+ documento.getTeNombre() + "\"");
				FileCopyUtils.copy(fis, response.getOutputStream());
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				LOGGER.error(e1.getMessage(),e1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				LOGGER.error(e.getMessage(),e);
			}
		
	}

	@Override
	public void deleteDocument(String idDocumento) {

		DocumentoVO documento = documentoDAO.findById(new Integer(idDocumento));
		
		File file = new File(documento.getTePath());
		
		file.delete();
		
		documentoDAO.remove(documento);
		
	}
	
//	@Override
//	public LinkedList<FileMeta> uploadDocumentComunidad(MultipartRequest request,
//			ComunidadVO comunidad) {
//		
//		
//		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
//		
//		// 1. build an iterator
//		Iterator<String> itr = request.getFileNames();
//		MultipartFile mpf = null;
//
//		// 2. get each file
//		while (itr.hasNext()) {
//
//			// 2.1 get next MultipartFile
//			mpf = request.getFile(itr.next());
//
//			
//			try {
//				FileMeta fileMeta = new FileMeta();
//				fileMeta.setFileName(mpf.getOriginalFilename());
//				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
//				fileMeta.setFileType(mpf.getContentType());
//
//				try {
//					fileMeta.setBytes(mpf.getBytes());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					LOGGER.error(e.getMessage(),e);
//				}
//				
//				
//				
//				OutputStream outputStream = null;
//				InputStream inputStream = null;
//				
//				String path = "/tmp/comunidades/" + comunidad.getTeNombre();
//						
//				// Creamos la estructura de directorios
//				new File (path).mkdirs();
//				
//				String fullPath = path + "/"
//						+ mpf.getOriginalFilename();
//				outputStream = new FileOutputStream(fullPath);
//
//				inputStream = mpf.getInputStream();
//
//				int readBytes = 0;
//				byte[] buffer = new byte[8192];
//
//				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
//					outputStream.write(buffer, 0, readBytes);
//				}
//				outputStream.close();
//				inputStream.close();
//
//				
//				DocumentoVO documento = new DocumentoVO();
//				documento.setComunidad(comunidad);
//				documento.setFeGuardado(new Date());
//				documento.setTeNombre(mpf.getOriginalFilename());
//				documento.setTePath(fullPath);
//				documentoDAO.persist(documento);
//				
//				ficherosSubidos.add(fileMeta);
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			}
//
//		}
//		// result will be like this
//		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//		return ficherosSubidos;
//		
//	}
//
//
//	@Override
//	public LinkedList<FileMeta> uploadDocumentActuacion(
//			MultipartRequest request, ActuacionVO actuacion) {
//
//		
//		
//		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
//		
//		// 1. build an iterator
//		Iterator<String> itr = request.getFileNames();
//		MultipartFile mpf = null;
//
//		// 2. get each file
//		while (itr.hasNext()) {
//
//			// 2.1 get next MultipartFile
//			mpf = request.getFile(itr.next());
//
//			
//			try {
//				FileMeta fileMeta = new FileMeta();
//				fileMeta.setFileName(mpf.getOriginalFilename());
//				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
//				fileMeta.setFileType(mpf.getContentType());
//
//				try {
//					fileMeta.setBytes(mpf.getBytes());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					LOGGER.error(e.getMessage(),e);
//				}
//				
//				
//				
//				OutputStream outputStream = null;
//				InputStream inputStream = null;
//				
//				String path = "/tmp/comunidades/" + actuacion.getSiniestro().getEmpresaComunidad().getComunidad().getTeNombre() + "/" + actuacion.getSiniestro().getEmpresaComunidad().getEmpresa().getTeNombre() + "/siniestros/" + actuacion.getSiniestro().getCnSiniestro() + "/" + actuacion.getCnActuacion();
//						
//				// Creamos la estructura de directorios
//				new File (path).mkdirs();
//				
//				String fullPath = path + "/"
//						+ mpf.getOriginalFilename();
//				outputStream = new FileOutputStream(fullPath);
//
//				inputStream = mpf.getInputStream();
//
//				int readBytes = 0;
//				byte[] buffer = new byte[8192];
//
//				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
//					outputStream.write(buffer, 0, readBytes);
//				}
//				outputStream.close();
//				inputStream.close();
//
//				
//				DocumentoVO documento = new DocumentoVO();
//				documento.setActuacion(actuacion);
//				documento.setFeGuardado(new Date());
//				documento.setTeNombre(mpf.getOriginalFilename());
//				documento.setTePath(fullPath);
//				documentoDAO.persist(documento);
//				
//				ficherosSubidos.add(fileMeta);
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			}
//
//		}
//		// result will be like this
//		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//		return ficherosSubidos;
//		
//	}
//
//	@Override
//	public LinkedList<FileMeta> uploadDocumentAvisoEmpresa(
//			MultipartRequest request, AvisoEmpresaVO avisoEmpresa) {
//
//		
//		
//		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
//		
//		// 1. build an iterator
//		Iterator<String> itr = request.getFileNames();
//		MultipartFile mpf = null;
//
//		// 2. get each file
//		while (itr.hasNext()) {
//
//			// 2.1 get next MultipartFile
//			mpf = request.getFile(itr.next());
//
//			
//			try {
//				FileMeta fileMeta = new FileMeta();
//				fileMeta.setFileName(mpf.getOriginalFilename());
//				fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
//				fileMeta.setFileType(mpf.getContentType());
//
//				try {
//					fileMeta.setBytes(mpf.getBytes());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					LOGGER.error(e.getMessage(),e);
//				}
//				
//				
//				
//				OutputStream outputStream = null;
//				InputStream inputStream = null;
//				
//				String path = "/tmp/comunidades/" + avisoEmpresa.getEmpresaComunidad().getComunidad().getTeNombre() + "/" + avisoEmpresa.getEmpresaComunidad().getEmpresa().getTeNombre() + "/avisos/" + avisoEmpresa.getCnAvisoEmpresa();
//						
//				// Creamos la estructura de directorios
//				new File (path).mkdirs();
//				
//				String fullPath = path + "/"
//						+ mpf.getOriginalFilename();
//				outputStream = new FileOutputStream(fullPath);
//
//				inputStream = mpf.getInputStream();
//
//				int readBytes = 0;
//				byte[] buffer = new byte[8192];
//
//				while ((readBytes = inputStream.read(buffer, 0, 8192)) != -1) {
//					outputStream.write(buffer, 0, readBytes);
//				}
//				outputStream.close();
//				inputStream.close();
//
//				
//				DocumentoVO documento = new DocumentoVO();
//				documento.setAvisoEmpresa(avisoEmpresa);
//				documento.setFeGuardado(new Date());
//				documento.setTeNombre(mpf.getOriginalFilename());
//				documento.setTePath(fullPath);
//				documentoDAO.persist(documento);
//				
//				ficherosSubidos.add(fileMeta);
//				
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				LOGGER.error(e.getMessage(),e);
//			}
//
//		}
//		// result will be like this
//		// [{"fileName":"app_engine-85x77.png","fileSize":"8 Kb","fileType":"image/png"},...]
//		return ficherosSubidos;
//		
//	}
}
