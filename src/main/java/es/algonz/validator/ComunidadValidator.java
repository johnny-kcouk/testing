package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.ComunidadVO;

public class ComunidadValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return ComunidadVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		
		//ComunidadVO comunidad = (ComunidadVO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caCif",  "error.requerido",new Object[]{"CIF"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teNombre",  "error.requerido",new Object[]{"Nombre"});
		
	}
}
