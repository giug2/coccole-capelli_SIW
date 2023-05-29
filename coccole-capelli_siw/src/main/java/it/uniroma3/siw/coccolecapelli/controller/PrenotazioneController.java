package it.uniroma3.siw.coccolecapelli.controller;

import static it.uniroma3.siw.coccolecapelli.model.Prenotazione.DIR_PAGES_PREN;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import com.polyspecialistcenter.aws.model.Professionista;

import it.uniroma3.siw.coccolecapelli.controller.validator.PrenotazioneValidator;
import it.uniroma3.siw.coccolecapelli.model.Disponibilita;
import it.uniroma3.siw.coccolecapelli.model.Prenotazione;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.model.User;
import it.uniroma3.siw.coccolecapelli.service.DisponibilitaService;
import it.uniroma3.siw.coccolecapelli.service.PrenotazioneService;
import it.uniroma3.siw.coccolecapelli.service.UtenteService;

@Controller
public class PrenotazioneController {
	
	@Autowired
	private PrenotazioneService prenotazioneService;
	
	@Autowired
	private DisponibilitaService disponibilitaService;
	
	@Autowired
	private PrenotazioneValidator prenotazioneValidator;
	
	@Autowired
	private UtenteService utenteService;
	
	@GetMapping("/profile/prenotazioni/{id}")
	public String getPrenotazioni(@PathVariable("id") Long id, Model model) {
		User utente = this.utenteService.getUser(id);
		model.addAttribute("prenotazioni", utente.getPrenotazioni());
		
		return DIR_PAGES_PREN + "elencoPrenotazioni";
	}
	
	@GetMapping("/profile/prenotazione/add/{id}")
    public String addPrenotazione(@PathVariable("id") Long id, Model model) {
        model.addAttribute("idUtente", id);
        return DIR_PAGES_PREN + "elencoServiziPrenotazione";
    }
	
	@GetMapping("/profile/prenotazione/disponibilita/{idU}")
	public String selectDisponibilita(@PathVariable("idU") Long idUtente, 
									  Model model) {
		model.addAttribute("idUtente", idUtente);
		model.addAttribute("prenotazione", new Prenotazione());
		
		Professionista p = this.;
		model.addAttribute("disponibilitaList", this.disponibilitaService.findByParrAndActive(p));
		
		
		return DIR_PAGES_PREN + "elencoDisponibilitaPrenotazione";
	}
	
	@GetMapping("/profile/prenotazione/add/{idU}/{idD}")
	public String addPrenotazione(@Valid @ModelAttribute("prenotazione") Prenotazione p,
								  BindingResult bindingResult,
								  @PathVariable("idU") Long idUtente,
								  @PathVariable("idD") Long idDisponibilita,
								  Model model) {
		
		User u = this.utenteService.getUser(idUtente);
		Disponibilita d = this.disponibilitaService.findById(idDisponibilita);
		Parrucchiere parr = p.getParrucchiere();
		p.setParrucchiere(parr);
		p.setCliente(u);
		p.setDisponibilita(d); 
		d.setActive(false);
		
		this.prenotazioneValidator.validate(p, bindingResult);
		if(!bindingResult.hasErrors()) {
			this.utenteService.addPrenotazione(u, p);			
			return "redirect:/profile/prenotazioni/" + u.getId();
		}
		
		//da modellare in caso di errori
		return DIR_PAGES_PREN + "riepilogoPrenotazione";
	}
	
	@GetMapping("/profile/delete/{id}")
	public String deletePrenotazione(@PathVariable("id") Long id, Model model) {
		Prenotazione p = this.prenotazioneService.findById(id);
		User u = p.getCliente();
		Disponibilita d = p.getDisponibilita();
		//Parrucchiere = p.getParrucchiere();
		d.setActive(true);
		
		this.utenteService.deletePrenotazione(u, p);
		this.prenotazioneService.delete(p);
		
		return "redirect:/profile/prenotazioni/" + u.getId();
	}
	
}