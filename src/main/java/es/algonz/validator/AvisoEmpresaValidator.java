package es.algonz.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.AvisoEmpresaVO;

public class AvisoEmpresaValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return AvisoEmpresaVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "estado.cnEstado",  "error.requerido",new Object[]{"Estado"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feInicio",  "error.requerido",new Object[]{"Fecha inicio"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feVencimiento",  "error.requerido",new Object[]{"Fecha vencimiento"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "teDescripcion",  "error.requerido",new Object[]{"Descripción"});
		
		
		
		AvisoEmpresaVO avisoEmpresa = (AvisoEmpresaVO)target;
		if (avisoEmpresa.getFeInicio() != null && avisoEmpresa.getFeVencimiento() != null && avisoEmpresa.getFeVencimiento().before(avisoEmpresa.getFeInicio())){
			errors.rejectValue("feInicio","error.fechaInicioMayorFechaFin",new Object[]{"Fecha inicio", "Fecha vencimiento"}, "");
 
		}
		
		
		
		
	}
}
