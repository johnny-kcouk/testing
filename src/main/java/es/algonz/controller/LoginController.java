package es.algonz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.UsuarioVO;
import es.algonz.web.utils.SessionKeys;

@Controller
public class LoginController
{
	@Autowired
	private ControladorUtils controladorUtils;

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String printWelcome(Model model, HttpSession session)
	{
		UsuarioVO usuario = controladorUtils.loadUser(session);
		model.addAttribute("username", usuario.getIdSistema());
		// Redirigimos al listado de alarmas
		return "forward:/action/alarmas/listado";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model)
	{
		return "login";
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(Model model)
	{
		model.addAttribute("error", "true");
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model, HttpSession session)
	{
		session.removeAttribute(SessionKeys.USUARIO);
		return "forward:/action/welcome";
	}
}