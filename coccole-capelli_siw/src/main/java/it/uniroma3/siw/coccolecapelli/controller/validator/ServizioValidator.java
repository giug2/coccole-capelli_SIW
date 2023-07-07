package it.uniroma3.siw.coccolecapelli.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.coccolecapelli.model.Servizio;
import it.uniroma3.siw.coccolecapelli.service.ServizioService;

@Component
public class ServizioValidator implements Validator {

	@Autowired
	private ServizioService servizioService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Servizio.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Servizio servizio = (Servizio) target;
		float prezzo = servizio.getPrezzo();
		
		if (prezzo <= 0)
			errors.rejectValue("servizio", "prezzo");
		
		if(this.servizioService.alreadyExists(servizio))
			errors.reject("duplicate.servizio");
	}
}
