package es.algonz.validator;


import org.apache.commons.validator.GenericValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import es.algonz.domain.EmpresaComunidadVO;

public class EmpresaComunidadValidator implements Validator {

	@Override
	public boolean supports(Class<?> clase) {
		return EmpresaComunidadVO.class.equals(clase);
	}

	@Override
	public void validate(Object target, Errors errors) {	
	
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "empresa.cnEmpresa",  "error.requerido",new Object[]{"Empresa"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feInicio",  "error.requerido",new Object[]{"Fecha inicio"});
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "feFin",  "error.requerido",new Object[]{"Fecha fin"});
		
		
		EmpresaComunidadVO empresaComunidad = (EmpresaComunidadVO)target;
		
		if (empresaComunidad.getEmpresa().getCnEmpresa() == null || GenericValidator.isBlankOrNull(empresaComunidad.getEmpresa().getCnEmpresa().toString())){
			errors.rejectValue("empresa.cnEmpresa",  "error.requerido",new Object[]{"Empresa"}, "");
		}
		
		if (empresaComunidad.getFeInicio() != null && empresaComunidad.getFeFin() != null && empresaComunidad.getFeFin().before(empresaComunidad.getFeInicio())){
			errors.rejectValue("feInicio","error.fechaInicioMayorFechaFin",new Object[]{"Fecha inicio", "Fecha fin"}, "");
 
		}
		
		
		
	}
}
