package es.algonz.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
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

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.PortalVO;
import es.algonz.domain.PredioVO;
import es.algonz.service.PortalManager;
import es.algonz.service.PredioManager;
import es.algonz.validator.PredioValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("predios")
public class PredioController implements Printable{
	@Autowired
	private PredioManager predioManager;
	@Autowired
	private PortalManager portalManager;
	@Autowired
	private CombosUtils comboUtils;

	private static final Log LOGGER = LogFactory.getLog(PredioController.class);

	@InitBinder(RequestKeys.PREDIO)
	protected void predio(WebDataBinder binder) {
		binder.setValidator(new PredioValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<PredioVO> listaPredios = null;
		listaPredios = predioManager.getPredios(null);
		model.addAttribute(RequestKeys.LISTA_PREDIOS,
				listaPredios);
		return "listadoPredios";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				PredioVO predio  = predioManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.PREDIO, predio);
				
				//Cargamos el combo de plantas
				model.addAttribute("plantasCombo", comboUtils.loadPlantas());
				//Cargamos el combo de representantes
				model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
			
		}
		return "detallePredio";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		PredioVO predio = new PredioVO();
		if (id != null) {
			predio  = predioManager.findById(new Integer (id).intValue());
			try{
				predioManager.remove(predio);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/portales/editar?id=" + predio.getPortal().getCnPortal();
		}
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
				redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Eliminado correctamente");
		}
		//return "forward:/action/predios/listado";
		return "redirect:/action/portales/editar?id=" + predio.getPortal().getCnPortal();
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		PredioVO predio = new PredioVO();		
		model.addAttribute(RequestKeys.PREDIO, predio);
		//Cargamos el combo de plantas
		model.addAttribute("plantasCombo", comboUtils.loadPlantas());
		//Cargamos el combo de representantes
		model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
		return "detallePredio";
	}
	
	@RequestMapping(value = "/nuevoPredio", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PORTAL) String codPortal) {
		PredioVO predio = new PredioVO();			
		if(!GenericValidator.isBlankOrNull(codPortal)){
			PortalVO portal = portalManager.findById(new Integer (codPortal));
			predio.setPortal(portal);
		}
		
		model.addAttribute(RequestKeys.PREDIO, predio);
		//Cargamos el combo de plantas
		model.addAttribute("plantasCombo", comboUtils.loadPlantas());
		//Cargamos el combo de representantes
		model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
		return "detallePredio";
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.PREDIO) PredioVO predio,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.PREDIO, predio);
			//Cargamos el combo de plantas
			model.addAttribute("plantasCombo", comboUtils.loadPlantas());
			//Cargamos el combo de representantes
			model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
			return "detallePredio";
		}
		if (predio != null) {
			
			// Para permitir que guarde sin representante
			if (predio.getTipoRepresentante() == null  || predio.getTipoRepresentante().getCnTipoRepresentante() == null || GenericValidator.isBlankOrNull(predio.getTipoRepresentante().getCnTipoRepresentante().toString())){
				predio.setTipoRepresentante(null);
			}
			
			// Fin de la busqueda de repetidos
			// Debemos buscar si ya existe la combinación planta / predio para ese portal
			
			PredioVO predioSearch = new PredioVO();
			predioSearch.setPortal(predio.getPortal());
			predioSearch.setPlanta(predio.getPlanta());
			//if (predio.getTipoPredio().getCnTipoPredio() == 1)
				predioSearch.setTePredio(predio.getTePredio());
//			else
//				predioSearch.setTePlaza(predio.getTePlaza());
				
			List<PredioVO> prediosList = predioManager.getPredios(predioSearch);
			if(prediosList!= null && prediosList.size() > 0){
				
				PredioVO encontrado = prediosList.get(0);
				
				if (predio.getCnPredio() == null || predio.getCnPredio().intValue() != encontrado.getCnPredio().intValue()){
				// Si es null es que estoy insertando y encontre uno repetido. ERROR
					// Si los cn son distintos es que estoy actualizando y encontre otro que coincidia. ERROR
//					if (predio.getTipoPredio().getCnTipoPredio() == 0)
						binding.rejectValue("tePredio", null, "El predio ya existe");
//					else
//						binding.rejectValue("tePlaza", null, "La plaza ya existe");
	
					
					model.addAttribute(RequestKeys.PREDIO, predio);
					//Cargamos el combo de plantas
					model.addAttribute("plantasCombo", comboUtils.loadPlantas());
					//Cargamos el combo de representantes
					model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
					return "detallePredio";
				}
				
			}
			
			// Fin de la busqueda de repetidos
			
			if (predio.getCnPredio() != null && !StringUtils.isBlank(predio.getCnPredio().toString())){
				predioManager.merge(predio);
			}
			else {
				predioManager.persist(predio);
			}
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "redirect:/action/portales/editar?id=" + predio.getPortal().getCnPortal();
	}
	
	

    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static String reportTemplateUrl = "etiquetasSobre.jrxml";
	@RequestMapping(value = "/imprimirPropietario", method = RequestMethod.GET)
	public String imprimirPropietario(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PREDIO) String codPredio, RedirectAttributes redirectAttrs, HttpServletResponse response) {
		
		
		
		try
        {
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(resourceAsStream);
            //compile the jasperDesign
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
    	
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
                
                PredioVO predio =  predioManager.findById(new Integer(codPredio));
                String linea1, linea2, linea3, linea4 = ""; 

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
    			}else{
    			// Imprimimos la dirección secundaria
    				linea1 = predio.getTerceroByCnPropietario().getNombreCompleto();
 				   params.put("linea1", linea1);
    			    String direccion = predio.getTerceroByCnPropietario().getTeDireccionSecundaria();
    			    String[] labels = direccion.split("\n");
    			    

    				int start = 0;
    				int end   = labels.length;
    				for (int line=start; line<end; line++) {
    				   params.put("linea"+(line+2), labels[line]);
    				}
    			}
                
              
                jasperPrint = JasperFillManager.fillReport(jasperReport, params,  new JREmptyDataSource());
                 
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
         
         


 		return "redirect:/action/predios/editar?id=" + codPredio;
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);

    		return "redirect:/action/predios/editar?id=" + codPredio;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);

			return "redirect:/action/predios/editar?id=" + codPredio;
		} 
		
	}
	
	
	
	@RequestMapping(value = "/imprimirConyuge", method = RequestMethod.GET)
	public String imprimirConyuge(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PREDIO) String codPredio, RedirectAttributes redirectAttrs, HttpServletResponse response) {
		
		
		
		try
        {
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(resourceAsStream);
            //compile the jasperDesign
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
    	
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                Map<String, Object> params = new HashMap<String, Object>();
                params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
                
                PredioVO predio =  predioManager.findById(new Integer(codPredio));
                String linea1, linea2, linea3, linea4 = ""; 

    			if (GenericValidator.isBlankOrNull(predio.getTerceroByCnConyuge().getTeDireccionSecundaria())){
    			// Imprimimos la dirección primaria
    				
    				linea1	=  predio.getTerceroByCnConyuge().getNombreCompleto();
    				linea2 = predio.getPortal().getTeCalle() + " " 
    						 + predio.getPortal().getTeNombre() + "     " + predio.getPlanta().getTePlanta() + " - " + predio.getTePredio();
    				linea3 = predio.getPortal().getComunidad().getTeCp() + " GIJÓN";
    				linea4 =  "ASTURIAS - ESPAÑA";

  				   params.put("linea1", linea1);
 				   params.put("linea2", linea2);
 				   params.put("linea3", linea3);
 				   params.put("linea4", linea4);
    			}else{
    			// Imprimimos la dirección secundaria
    				linea1 = predio.getTerceroByCnConyuge().getNombreCompleto();
 				   params.put("linea1", linea1);
    			    String direccion = predio.getTerceroByCnConyuge().getTeDireccionSecundaria();
    			    String[] labels = direccion.split("\n");
    			    

    				int start = 0;
    				int end   = labels.length;
    				for (int line=start; line<end; line++) {
    				   params.put("linea"+(line+2), labels[line]);
    				}
    			}
                
              
                jasperPrint = JasperFillManager.fillReport(jasperReport, params,  new JREmptyDataSource());

                 
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
         
 		return "redirect:/action/predios/editar?id=" + codPredio;
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);

    		return "redirect:/action/predios/editar?id=" + codPredio;
        } catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage(),e);

			return "redirect:/action/predios/editar?id=" + codPredio;
		} 
		
	}
	
	
	public static PredioVO predioPrint;


	@Override
	 public int print (Graphics g1, PageFormat f, int pageIndex)
	   {
		   Graphics2D g = (Graphics2D) g1; 		   
		
		   if (pageIndex > 0) {
		         return NO_SUCH_PAGE;
		    }

			
			
			Font font = new Font("Serif", Font.PLAIN, 10);
			FontMetrics metrics = g.getFontMetrics(font);
			int lineHeight = metrics.getHeight()+2;
			
			Paper paper = new Paper();
			paper.setSize(312, 652); 
			
			paper.setImageableArea(20, 20, 250, 600);
			f.setPaper(paper);
			f.setOrientation(PageFormat.PORTRAIT);
					
		
		     //--- Translate the origin to 0,0 for the top left corner
		      g.translate(f.getImageableX(), f
		          .getImageableY());

		      //--- Set the default drawing color to black
		      g.setPaint(Color.black);
			
		      Rectangle2D.Double border = new Rectangle2D.Double(0, 0, f
		          .getImageableWidth(), f.getImageableHeight());
		      g.draw(border);
			
			
		    int x = 130;  
			int y = 270; 
			

		        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);
			
			

			if (GenericValidator.isBlankOrNull(predioPrint.getTerceroByCnPropietario().getTeDireccionSecundaria())){
			// Imprimimos la dirección primaria
				
				label_line(g, x, y, predioPrint.getTerceroByCnPropietario().getNombreCompleto());
				x -= lineHeight;
				label_line(g, x, y, predioPrint.getPortal().getTeCalle() + " " 
						 + predioPrint.getPortal().getTeNombre() + "     " + predioPrint.getPlanta().getTePlanta() + " - " + predioPrint.getTePredio());
				x -= lineHeight;
				label_line(g, x, y, predioPrint.getPortal().getComunidad().getTeCp() + " GIJÓN");
				x -= lineHeight;
				label_line(g, x, y, "ASTURIAS - ESPAÑA");
				x -= lineHeight;
				
				
				
			}else{
			// Imprimimos la dirección secundaria
				label_line(g, x, y, predioPrint.getTerceroByCnPropietario().getNombreCompleto());
			    String direccion = predioPrint.getTerceroByCnPropietario().getTeDireccionSecundaria();
			    String[] labels = direccion.split("\n");
			    

				int start = 0;
				int end   = labels.length;
			    
				for (int line=start; line<end; line++) {
			    x -= lineHeight;
				label_line(g, x, y, labels[line]);
			}
			}
		



		    return PAGE_EXISTS;
			
	   }

	  
	  private void label_line(Graphics g, double x, double y, String label) {

		     Graphics2D g2D = (Graphics2D)g;
		     
		     double theta = 90 * java.lang.Math.PI/180;

		    // Create a rotation transformation for the font.
		    AffineTransform fontAT = new AffineTransform();

		    // get the current font
		    Font theFont = g2D.getFont();

		    // Derive a new font using a rotatation transform
		    fontAT.rotate(theta);
		    Font theDerivedFont = theFont.deriveFont(fontAT);

		    // set the derived font in the Graphics2D context
		    g2D.setFont(theDerivedFont);

		    // Render a string using the derived font
		    g2D.drawString(label, (int)x, (int)y);

		    // put the original font back
		    g2D.setFont(theFont);
		}

}
