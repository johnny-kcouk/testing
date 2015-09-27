package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.SiniestroVO;

public class SiniestroValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return SiniestroVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empresaComunidad.cnEmpresaComunidad",  "error.requerido",new Object[]{"Empresa"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teNombre",  "error.requerido",new Object[]{"Nombre"});

	}
}
