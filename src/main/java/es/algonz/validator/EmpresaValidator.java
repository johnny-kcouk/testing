package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.EmpresaVO;

public class EmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return EmpresaVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
		//EmpresaVO empresa = (EmpresaVO) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "tipoEmpresa.cnTipoEmpresa",  "error.requerido",new Object[]{"Tipo empresa"});
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "caCif",  "error.requerido",new Object[]{"CIF"});
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teNombre",  "error.requerido",new Object[]{"Nombre"});

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teTlfFijo",  "error.requerido",new Object[]{"Teléfono"});
		
	}
}
