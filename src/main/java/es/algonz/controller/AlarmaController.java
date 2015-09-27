package es.algonz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.ActuacionVO;
import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.domain.UsuarioVO;
import es.algonz.service.ActuacionManager;
import es.algonz.service.AvisoEmpresaManager;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("alarmas")
public class AlarmaController {
	@Autowired
	private ActuacionManager actuacionManager;
	@Autowired
	private AvisoEmpresaManager avisoEmpresaManager;
	
	@Autowired
	private ControladorUtils controladorUtils;


	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {
		
		
		UsuarioVO usuario = controladorUtils.loadUser(session);
		
		String idUsuario = usuario.getIdUsuario();
		
		List<ActuacionVO> listaActuaciones  = actuacionManager.getActuacionesProximoVencimiento();
		model.addAttribute(RequestKeys.LISTA_ACTUACIONES, listaActuaciones);
		
		
		List<ActuacionVO> listaActuacionesUsuario = actuacionManager.getActuacionesUsuarioProximoVencimiento(idUsuario);
		model.addAttribute(RequestKeys.LISTA_ACTUACIONES_USUARIO, listaActuacionesUsuario);
		

		List<AvisoEmpresaVO> listaAvisos = avisoEmpresaManager.getAvisosEmpresaProximoVencimiento();
		model.addAttribute(RequestKeys.LISTA_AVISOS_EMPRESA, listaAvisos);
		
		List<AvisoEmpresaVO> listaAvisosUsuario = avisoEmpresaManager.getAvisosEmpresaUsuarioProximoVencimiento(idUsuario);
		model.addAttribute(RequestKeys.LISTA_AVISOS_EMPRESA_USUARIO, listaAvisosUsuario);


		return "listadoAlarmas";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

}