package es.algonz.validator;

import org.apache.commons.lang.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.UsuarioVO;

public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return UsuarioVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "IdSistema",  "error.requerido",new Object[]{"Usuario"});


		UsuarioVO usuario = (UsuarioVO)target;
		
		
		
		if (StringUtils.isNotBlank(usuario.getPwdSistemaOld())){
			if (!usuario.getPwdSistema().equalsIgnoreCase(usuario.getPwdSistemaOld())){
				errors.rejectValue("pwdSistemaOld",  "error.paswordOriginalNoCoincide",null, "");
				
			}
			
		}
		
		if (StringUtils.isNotBlank(usuario.getPwdSistemaOld()) || StringUtils.isNotBlank(usuario.getPwdSistemaNew()) || StringUtils.isNotBlank(usuario.getPwdSistemaConfirm()) ){
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwdSistemaOld",  "error.requerido",new Object[]{"Password actual"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwdSistemaNew",  "error.requerido",new Object[]{"Nuevo Password"});
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwdSistemaConfirm",  "error.requerido",new Object[]{"Confirmar Password"});

			
		}

		
		
		if ( StringUtils.isNotBlank(usuario.getPwdSistemaNew()) && StringUtils.isNotBlank(usuario.getPwdSistemaConfirm()) && !usuario.getPwdSistemaNew().equals(usuario.getPwdSistemaConfirm()) ){
			errors.rejectValue("pwdSistemaConfirm",  "error.passworNuevoNoCoincide",null, "");				
		}

			
		
	}
	
	
}
