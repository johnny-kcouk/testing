package es.algonz.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.ComunidadVO;
import es.algonz.domain.EmpresaComunidadVO;
import es.algonz.domain.PropertyBean;
import es.algonz.service.ComunidadManager;
import es.algonz.service.EmpresaComunidadManager;
import es.algonz.validator.EmpresaComunidadValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("empresasComunidad")
public class EmpresaComunidadController {
	@Autowired
	private EmpresaComunidadManager empresaComunidadManager;
	@Autowired
	private ComunidadManager comunidadManager;
	@Autowired
	private CombosUtils combosUtils;

	@InitBinder(RequestKeys.EMPRESA_COMUNIDAD)
	protected void empresaComunidad(WebDataBinder binder) {
		binder.setValidator(new EmpresaComunidadValidator());
		
		// Para que salga la fecha formateada al entrar en los detalles
		// y permitir guardar vacias
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
		
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<EmpresaComunidadVO> listaEmpresasComunidad = null;
		listaEmpresasComunidad = empresaComunidadManager.getEmpresasComunidad(null);
		model.addAttribute(RequestKeys.LISTA_EMPRESAS_COMUNIDAD,
				listaEmpresasComunidad);
		return "listadoEmpresasComunidad";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				EmpresaComunidadVO empresaComunidad  = empresaComunidadManager.findById(new Integer (id).intValue());
				model.addAttribute(RequestKeys.EMPRESA_COMUNIDAD, empresaComunidad);
				

				// Cargamos el combo de tipos de empresa
				model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
				model.addAttribute("empresasCombo", combosUtils.loadEmpresasByTipo(empresaComunidad.getEmpresa().getTipoEmpresa().getCnTipoEmpresa()));			
		}


		
		return "detalleEmpresaComunidad";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		String idComunidad ="";
		if (id != null) {
			EmpresaComunidadVO empresaComunidad  = empresaComunidadManager.findById(new Integer (id).intValue());
				idComunidad = empresaComunidad.getComunidad().getCnComunidad().toString();
				try{
				empresaComunidadManager.remove(empresaComunidad);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/comunidades/editar?id=" + idComunidad;
		}
				redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Eliminado correctamente");
		}
		return "redirect:/action/comunidades/editar?id=" + idComunidad;
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		EmpresaComunidadVO empresaComunidad = new EmpresaComunidadVO();		
		model.addAttribute(RequestKeys.EMPRESA_COMUNIDAD, empresaComunidad);
		// Cargamos el combo de tipos de empresa
		model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
		return "detalleEmpresaComunidad";
	}
	
	@RequestMapping(value = "/nuevaEmpresaComunidad", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad) {
		EmpresaComunidadVO empresaComunidad = new EmpresaComunidadVO();			
		if(!GenericValidator.isBlankOrNull(codComunidad)){
			ComunidadVO comunidad = comunidadManager.findById(new Integer (codComunidad));
			empresaComunidad.setComunidad(comunidad);
		}
		
		model.addAttribute(RequestKeys.EMPRESA_COMUNIDAD, empresaComunidad);
		// Cargamos el combo de tipos de empresa
		model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
		return "detalleEmpresaComunidad";
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.EMPRESA_COMUNIDAD) EmpresaComunidadVO empresaComunidad,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.EMPRESA_COMUNIDAD, empresaComunidad);
			// Cargamos el combo de tipos de empresa
			model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());

			if (empresaComunidad != null && empresaComunidad.getEmpresa() != null && empresaComunidad.getEmpresa().getTipoEmpresa() != null) {
				model.addAttribute("empresasCombo", combosUtils.loadEmpresasByTipo(empresaComunidad.getEmpresa().getTipoEmpresa().getCnTipoEmpresa()));		
			}
			
			return "detalleEmpresaComunidad";
		}
		if (empresaComunidad != null) {
			if (empresaComunidad.getCnEmpresaComunidad() != null && !StringUtils.isBlank(empresaComunidad.getCnEmpresaComunidad().toString()))
				empresaComunidadManager.merge(empresaComunidad);
			else {
				empresaComunidadManager.persist(empresaComunidad);
			}
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "redirect:/action/comunidades/editar?id=" + empresaComunidad.getComunidad().getCnComunidad();
	}
	
	
	
	@RequestMapping(value = "cargarEmpresas", method = RequestMethod.POST)
	public @ResponseBody
	List<PropertyBean> cargarEmpresas(
			@RequestParam(value = "idTipoEmpresa") String idTipoEmpresa) {

		List<PropertyBean> listaPB = new ArrayList<PropertyBean>();

		if (idTipoEmpresa == null) {
			return listaPB;
		}

		listaPB = combosUtils.loadEmpresasByTipo(new Integer(idTipoEmpresa));	
		
		return listaPB;

	}

}