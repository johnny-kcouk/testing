package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.PredioVO;

public class PredioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return PredioVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoPredio.cnTipoPredio",  "error.requerido",new Object[]{"Tipo predio"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "planta.cnPlanta",  "error.requerido",new Object[]{"Planta"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terceroByCnPropietario.teNombre",  "error.requerido",new Object[]{"Nombre"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terceroByCnPropietario.teApellido1",  "error.requerido",new Object[]{"Número"});
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "terceroByCnPropietario.teTlfFijo",  "error.requerido",new Object[]{"Teléfono"});
		
	}
}
