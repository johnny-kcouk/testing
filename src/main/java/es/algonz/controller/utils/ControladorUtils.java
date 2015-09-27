package es.algonz.controller.utils;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import es.algonz.domain.UsuarioVO;
import es.algonz.service.UsuarioManager;
import es.algonz.web.utils.SessionKeys;

@Service("controladorUtils")
public class ControladorUtils
{
	@Autowired
	private UsuarioManager usuarioManager;

	public UsuarioVO loadUser(HttpSession session)
	{
		UsuarioVO usuario = (UsuarioVO) session.getAttribute(SessionKeys.USUARIO);
		if (usuario == null) {
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			usuario = usuarioManager.getUsuarioByIdSistema(user.getUsername());
			session.setAttribute(SessionKeys.USUARIO, usuario);
		}
		return usuario;
	}

}
