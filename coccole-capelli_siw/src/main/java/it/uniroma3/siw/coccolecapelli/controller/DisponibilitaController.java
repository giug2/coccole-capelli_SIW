package it.uniroma3.siw.coccolecapelli.controller;

import static it.uniroma3.siw.coccolecapelli.model.Disponibilita.DIR_ADMIN_PAGES_DISP;
import static it.uniroma3.siw.coccolecapelli.model.Disponibilita.DIR_PAGES_DISP;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.coccolecapelli.controller.validator.DisponibilitaValidator;
import it.uniroma3.siw.coccolecapelli.model.Disponibilita;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.service.DisponibilitaService;
import it.uniroma3.siw.coccolecapelli.service.ParrucchiereService;

@Controller
public class DisponibilitaController {
	
	@Autowired
	private DisponibilitaService disponibilitaService;
	
	@Autowired
	private DisponibilitaValidator disponibilitaValidator;
	
	@Autowired
	private ParrucchiereService parrucchiereService;
	
	
	/* METHODS GENERIC_USER */
	
	@GetMapping("/disponibilita/{id}")
	public String getDisponibilitaProfessionista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("disponibilitaList", this.disponibilitaService.findByParrAndActive(this.parrucchiereService.findById(id)));
		
		return DIR_PAGES_DISP + "elencoDisponibilita";
	}
	
	/* METHODS ADMIN */
	
	@GetMapping("/admin/disponibilita/{id}")
	public String getAdminDisponibilitaProfessionista(@PathVariable("id") Long id, Model model) {
		model.addAttribute("disponibilitaList", this.disponibilitaService.findByParrAndActive(this.parrucchiereService.findById(id)));
		model.addAttribute("idProfessionista", id);
		return DIR_ADMIN_PAGES_DISP + "adminElencoDisponibilita";
	}
	
	// --- INSERIMENTO
	
	@GetMapping("/admin/disponibilita/add/{id}")
	public String addGetDisponibilita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("idParrucchiere", id);
		model.addAttribute("disponibilita", new Disponibilita());
		
		return DIR_ADMIN_PAGES_DISP + "disponibilitaForm";
	}
	
	@PostMapping("/admin/disponibilita/add/{id}")
	public String addDisponibilita(@Valid @ModelAttribute("disponibilita") Disponibilita disponibilita, BindingResult bindingResult, 
									@PathVariable("id") Long id, Model model) {
		
		Parrucchiere parrucchiere = this.parrucchiereService.findById(id);
		disponibilita.setParrucchiere(parrucchiere);
		disponibilita.setActive(true);
		this.disponibilitaValidator.validate(disponibilita, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.parrucchiereService.addDisponibilita(parrucchiere, disponibilita);
			return this.getAdminDisponibilitaParrucchiere(id, model);
		}
		
		model.addAttribute("id", id);
		return DIR_ADMIN_PAGES_DISP + "disponibilitaForm";
	}
	
	// --- ELIMINAZIONE
	
	@GetMapping("/admin/disponibilita/delete/{id}")
	public String deleteDisponibilita(@PathVariable("id") Long id, Model model) {
		Disponibilita disponibilita = this.disponibilitaService.findById(id);		
		Parrucchiere p = this.parrucchiereService.findById(disponibilita.getParrucchiere().getId());
		
		p.getDisponibilita().remove(disponibilita);
		this.disponibilitaService.delete(disponibilita);
		this.parrucchiereService.save(p);	
		
		return "redirect:/admin/disponibilita/" + p.getId();
	}
	
	// --- MODIFICA
	
	@GetMapping("/admin/disponibilita/edit/{id}")
	public String editDisponibilita(@PathVariable("id") Long id, Model model) {
		model.addAttribute("disponibilita", this.disponibilitaService.findById(id));
		
		return DIR_ADMIN_PAGES_DISP + "editDisponibilita";
	}
	
	@PostMapping("/admin/disponibilita/edit/{id}")
	public String editDisponiblita(@Valid @ModelAttribute("disponibilita") Disponibilita disponibilita, 
								   BindingResult bindingResult, 
								   @PathVariable("id") Long id, 
								   Model model) {
		
		Disponibilita d = this.disponibilitaService.findById(id);
		disponibilita.setParrucchiere(d.getParrucchiere());
		this.disponibilitaValidator.validate(disponibilita, bindingResult);
		if(!bindingResult.hasErrors()) {
			
			this.disponibilitaService.update(d, disponibilita);
			
			return this.getAdminDisponibilitaParrucchiere(d.getParrucchiere().getId(), model);
		}
		
		disponibilita.setId(id);
		return DIR_ADMIN_PAGES_DISP + "editDisponibilita";
	}
	
}
