package it.uniroma3.siw.coccolecapelli.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.service.ParrucchiereService;

@Component
public class ParrucchiereValidator implements Validator {

	@Autowired
	private ParrucchiereService parrucchiereService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Parrucchiere.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(parrucchiereService.alreadyExists((Parrucchiere) target))
			errors.reject("duplicate.parrucchiere");
	}

}
