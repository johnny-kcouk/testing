package es.algonz.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.domain.DocumentoVO;
import es.algonz.domain.EmpresaComunidadVO;
import es.algonz.domain.FileMeta;
import es.algonz.service.AvisoEmpresaManager;
import es.algonz.service.DocumentoManager;
import es.algonz.service.EmpresaComunidadManager;
import es.algonz.validator.AvisoEmpresaValidator;
import es.algonz.web.utils.ConstantesKeys;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("avisosEmpresa")
public class AvisoEmpresaController {
	@Autowired
	private AvisoEmpresaManager avisoEmpresaManager;
	@Autowired
	private EmpresaComunidadManager empresaComunidadManager;
	@Autowired
	private CombosUtils combosUtils;

	@Autowired
	private DocumentoManager documentoManager;
	

	private static final Log LOGGER = LogFactory.getLog(AvisoEmpresaController.class);

	@InitBinder(RequestKeys.AVISO_EMPRESA)
	protected void avisoEmpresa(WebDataBinder binder) {
		binder.setValidator(new AvisoEmpresaValidator());
		
		
		// Para que salga la fecha formateada al entrar en los detalles
		// y permitir guardar vacias
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	   
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<AvisoEmpresaVO> listaAvisosEmpresa = null;
		listaAvisosEmpresa = avisoEmpresaManager.getAvisosEmpresa(null);
		model.addAttribute(RequestKeys.LISTA_AVISOS_EMPRESA,
				listaAvisosEmpresa);
		return "listadoAvisosEmpresa";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				AvisoEmpresaVO avisoEmpresa  = avisoEmpresaManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

				// Cargamos el combo de estados
				model.addAttribute("estadosCombo", combosUtils.loadEstados());
				
			
		}
		return "detalleAvisoEmpresa";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		
		String idEmpresaComunidad = "";
		if (id != null) {
			AvisoEmpresaVO avisoEmpresa  = avisoEmpresaManager.findById(new Integer (id).intValue());
			idEmpresaComunidad = avisoEmpresa.getEmpresaComunidad().getCnEmpresaComunidad().toString();
			try{
				avisoEmpresaManager.remove(avisoEmpresa);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/empresasComunidad/editar?id=" + idEmpresaComunidad;
		}
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		//return "forward:/action/avisosEmpresa/listado";

		return "redirect:/action/empresasComunidad/editar?id=" + idEmpresaComunidad;
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_EMPRESA_COMUNIDAD) String codEmpresaComunidad) {
		AvisoEmpresaVO avisoEmpresa = new AvisoEmpresaVO();	
		if(!GenericValidator.isBlankOrNull(codEmpresaComunidad)){
			EmpresaComunidadVO empresaComunidad = empresaComunidadManager.findById(new Integer (codEmpresaComunidad));
			avisoEmpresa.setEmpresaComunidad(empresaComunidad);
		}
		model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

		// Cargamos el combo de estados
		model.addAttribute("estadosCombo", combosUtils.loadEstados());
		return "detalleAvisoEmpresa";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.AVISO_EMPRESA) AvisoEmpresaVO avisoEmpresa,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

			// Cargamos el combo de estados
			model.addAttribute("estadosCombo", combosUtils.loadEstados());
			return "detalleAvisoEmpresa";
		}
		if (avisoEmpresa != null) {
			if (avisoEmpresa.getFeCierre() == null && ConstantesKeys.ESTADO_CERRADO.equalsIgnoreCase(avisoEmpresa.getEstado().getCnEstado().toString())){
				avisoEmpresa.setFeCierre(new Date());
			}
			
			if (avisoEmpresa.getCnAvisoEmpresa() != null && !StringUtils.isBlank(avisoEmpresa.getCnAvisoEmpresa().toString()))
				avisoEmpresaManager.merge(avisoEmpresa);
			else {
				avisoEmpresaManager.persist(avisoEmpresa);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");

			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}

		return "redirect:/action/empresasComunidad/editar?id=" + avisoEmpresa.getEmpresaComunidad().getCnEmpresaComunidad();
	}
	

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	public  @ResponseBody LinkedList<FileMeta> uploadDocument(Model model, HttpSession session, MultipartRequest request, @RequestParam(RequestKeys.CODIGO_AVISO_EMPRESA) String codAvisoEmpresa) {
		
		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
		
		if(!GenericValidator.isBlankOrNull(codAvisoEmpresa)){
			AvisoEmpresaVO avisoEmpresa = avisoEmpresaManager.findById(new Integer (codAvisoEmpresa));

			DocumentoVO documento = new DocumentoVO();
			documento.setAvisoEmpresa(avisoEmpresa);
			ficherosSubidos = documentoManager.uploadDocument(request, documento);
		}
		
		return ficherosSubidos;
	}
	
	
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public void downloadDocument(HttpServletResponse response,  @RequestParam(RequestKeys.ID) String idDocumento) {
		
		documentoManager.downloadDocument(response, idDocumento);
		
	}
	

	@RequestMapping(value = "/deleteDocument", method = RequestMethod.GET)
	public String deleteDocument(Model model,
			@RequestParam(RequestKeys.CODIGO_AVISO_EMPRESA) String codAvisoEmpresa, @RequestParam(RequestKeys.ID) String idDocumento, HttpSession session, RedirectAttributes redirectAttrs) {
		
		documentoManager.deleteDocument(idDocumento);

		redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Documento eliminado correctamente");
		
		return "redirect:/action/avisosEmpresa/editar?id=" + codAvisoEmpresa;
		
		
	}
	
	
	


    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static String reportTemplateUrl = "avisoEmpresa.jrxml";
    
	@RequestMapping(value = "/informeAviso", method = RequestMethod.GET)
	public String informeAviso(Model model, @RequestParam(RequestKeys.CODIGO_AVISO_EMPRESA) String codAvisoEmpresa, HttpSession session, HttpServletResponse response) {

		try
        {
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(resourceAsStream);
            //compile the jasperDesign
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
    		
  		   List<AvisoEmpresaVO> dataReport = new LinkedList<AvisoEmpresaVO>();
            
            if (codAvisoEmpresa != null) {	
            	AvisoEmpresaVO avisoEmpresa  = avisoEmpresaManager.findById(new Integer (codAvisoEmpresa).intValue());
  				dataReport.add(avisoEmpresa);
            }
            
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);

                BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("../../images/ALGONZ-logo.jpg"));
                params.put("logo", image );
              
                //fill the ready report with data and parameter
                jasperPrint = JasperFillManager.fillReport(jasperReport, params,  new JRBeanCollectionDataSource(dataReport));
                //view the report using JasperViewer
                // JasperViewer.viewReport(jasperPrint);
                 
            }
            finally {
                if (virtualizer != null) virtualizer.cleanup();
            }    
           
         String fileName = "UserReport.pdf";
         response.setHeader("Content-Disposition", "inline; filename="+ fileName);
         response.setContentType("application/pdf");


         JRPdfExporter exporter = new JRPdfExporter(); 
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
         exporter.exportReport();
         
         


         return this.editar(model, codAvisoEmpresa, session);
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);

            return this.editar(model, codAvisoEmpresa, session);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);

            return this.editar(model, codAvisoEmpresa, session);
		} 
		
	}
	

}