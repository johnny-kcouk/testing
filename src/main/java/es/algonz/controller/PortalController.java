package es.algonz.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRPrintPage;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PortalVO;
import es.algonz.domain.PredioVO;
import es.algonz.service.ComunidadManager;
import es.algonz.service.PortalManager;
import es.algonz.validator.PortalValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("portales")
public class PortalController {
	@Autowired
	private PortalManager portalManager;
	@Autowired
	private ComunidadManager comunidadManager;
	@Autowired
	private ControladorUtils controladorUtils;

	private static final Log LOGGER = LogFactory.getLog(PortalController.class);

	@InitBinder(RequestKeys.PORTAL)
	protected void portal(WebDataBinder binder) {
		binder.setValidator(new PortalValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<PortalVO> listaPortales = null;
		listaPortales = portalManager.getPortales(null);
		model.addAttribute(RequestKeys.LISTA_PORTALES,
				listaPortales);
		return "listadoPortales";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				PortalVO portal  = portalManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.PORTAL, portal);
			
		}
		return "detallePortal";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		PortalVO portal = new PortalVO();
		if (id != null) {
			 portal  = portalManager.findById(new Integer (id).intValue());
			 try{
				portalManager.remove(portal);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
		}
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
				redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Eliminado correctamente");
			}
			return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		PortalVO portal = new PortalVO();		
		model.addAttribute(RequestKeys.PORTAL, portal);
		return "detallePortal";
	}

	@RequestMapping(value = "/nuevoPortal", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad) {
		PortalVO portal = new PortalVO();		
		if(!GenericValidator.isBlankOrNull(codComunidad)){
			ComunidadVO comunidad = comunidadManager.findById(new Integer (codComunidad));
			portal.setComunidad(comunidad);
		}
		
		model.addAttribute(RequestKeys.PORTAL, portal);
		return "detallePortal";
	}
	
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.PORTAL) PortalVO portal,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.PORTAL, portal);
			return "detallePortal";
		}
		if (portal != null) {
			if (portal.getCnPortal() != null && !StringUtils.isBlank(portal.getCnPortal().toString()))
				portalManager.merge(portal);
			else {
				portalManager.persist(portal);
			}
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
		}

	
	

    public static JasperPrint jasperPrint;
    public static JasperDesign jasperDesign;
    public static JasperReport jasperReport;
    public static String reportTemplateUrl = "etiquetasSobrePortal.jrxml";

	@RequestMapping(value = "/imprimirSobresPortal", method = RequestMethod.GET)
	public String imprimirSobresPortal(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PORTAL) String codPortal, RedirectAttributes redirectAttrs, HttpServletResponse response) {
		
		
		try
        {
			jasperPrint = null;
			
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));

            jasperDesign = JRXmlLoader.load(resourceAsStream);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);

        	
    		List<PredioVO> dataReport = new LinkedList<PredioVO>();
           
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
               
                
                PortalVO portal =  portalManager.findById(new Integer(codPortal));
                
                Set<PredioVO>  predios = (java.util.Set<PredioVO>) portal.getPredios();
                
                Iterator<PredioVO> itr = predios.iterator();
                
             //   List listaPDFs = new  ArrayList<Object>();
                while (itr.hasNext()){
                	
                	PredioVO predio = itr.next();
                	
                	dataReport.add(predio);
                	
		                
		                String linea1, linea2, linea3, linea4  = ""; 
		                
		
		    			if (GenericValidator.isBlankOrNull(predio.getTerceroByCnPropietario().getTeDireccionSecundaria())){
		    			// Imprimimos la dirección primaria
		    				
		    				linea1	=  predio.getTerceroByCnPropietario().getNombreCompleto();
		    				linea2 = predio.getPortal().getTeCalle() + " " 
		    						 + predio.getPortal().getTeNombre() + "     " + predio.getPlanta().getTePlanta() + " - " + predio.getTePredio();
		    				linea3 = predio.getPortal().getComunidad().getTeCp() + " GIJÓN";
		    				linea4 =  "ASTURIAS - ESPAÑA";
		
		  				   params.put("linea1", linea1);
		 				   params.put("linea2", linea2);
		 				   params.put("linea3", linea3);
		 				   params.put("linea4", linea4);
		 				   params.put("linea5", "");
		 				   params.put("linea6", "");
		    			}else{
		    			// Imprimimos la dirección secundaria
		    				linea1 = predio.getTerceroByCnPropietario().getNombreCompleto();
		 				   params.put("linea1", linea1);
		    			    String direccion = predio.getTerceroByCnPropietario().getTeDireccionSecundaria();
		    			    String[] labels = direccion.split("\n");
		    			    
		
		    				int start = 0;
		    				int end   = labels.length;
		    				
		    				
		    				// Reseteo los valores de los parametros

			 				   params.put("linea2", "");
			 				   params.put("linea3", "");
			 				   params.put("linea4", "");
			 				   params.put("linea5", "");
			 				   params.put("linea6", "");
		    				
		    				for (int line=start; line<end; line++) {
		    				   params.put("linea"+(line+2), labels[line]);
		    				}
		    			}

					if (jasperPrint != null) {

						JasperPrint jasperPrintAux = JasperFillManager
								.fillReport(jasperReport, params,
										new JREmptyDataSource());

						List<JRPrintPage> pages = jasperPrintAux.getPages();
						for (int j = 0; j < pages.size(); j++) {
							JRPrintPage object = (JRPrintPage) pages.get(j);
							jasperPrint.addPage(object);
						}

					} else {

						// fill the ready report with data and parameter
						jasperPrint = JasperFillManager.fillReport(
								jasperReport, params, new JREmptyDataSource());

						// view the report using JasperViewer
						// JasperViewer.viewReport(jasperPrint);
					}

				}
                
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


 		return "redirect:/action/portales/editar?id=" + codPortal;
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);

    		return "redirect:/action/portales/editar?id=" + codPortal;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);

			return "redirect:/action/portales/editar?id=" + codPortal;
		} 
		
	}
	

    public static JasperDesign jasperDesignResumen;
    public static JasperReport jasperReportResumen;
    public static String reportTemplateUrlResumen = "resumenSobrePortal.jrxml";
	@RequestMapping(value = "/imprimirResumenSobres", method = RequestMethod.GET)
	public String imprimirResumenSobres(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PORTAL) String codPortal, RedirectAttributes redirectAttrs, HttpServletResponse response) {
		
		
		try
        {
			jasperPrint = null;
		
            
            BufferedInputStream resourceAsStreamResumen = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrlResumen));
            //get report file and then load into jasperDesign
            jasperDesignResumen = JRXmlLoader.load(resourceAsStreamResumen);
            //compile the jasperDesign
            jasperReportResumen = JasperCompileManager.compileReport(jasperDesignResumen);

    		List<PredioVO> dataReport = new LinkedList<PredioVO>();
           
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                PortalVO portal =  portalManager.findById(new Integer(codPortal));
             
                
                Set<PredioVO>  predios = (java.util.Set<PredioVO>) portal.getPredios();
                
                Iterator<PredioVO> itr = predios.iterator();
                
             //   List listaPDFs = new  ArrayList<Object>();
                while (itr.hasNext()){
                	
                	PredioVO predio = itr.next();
                	
                	dataReport.add(predio);
                	
				}


                Map<String, Object> paramsResumen = new HashMap<String, Object>();
                paramsResumen.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
                BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("../../images/ALGONZ-logo.jpg"));
                paramsResumen.put("logo", image );
                
                paramsResumen.put("comunidad", portal.getComunidad().getTeNombre());
                paramsResumen.put("portal", portal.getTeCalle() + " " + portal.getTeNombre());


    				jasperPrint = JasperFillManager.fillReport(
    						jasperReportResumen, paramsResumen, 	new JRBeanCollectionDataSource(dataReport));

                
                
                
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


 		return "redirect:/action/portales/editar?id=" + codPortal;
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);

    		return "redirect:/action/portales/editar?id=" + codPortal;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);

			return "redirect:/action/portales/editar?id=" + codPortal;
		} 
		
	}
	
	
	
	
	
	
	
}