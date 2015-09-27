package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.PortalVO;

public class PortalValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return PortalVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teCalle",  "error.requerido",new Object[]{"Calle"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teNombre",  "error.requerido",new Object[]{"Número"});
		
	}
}
