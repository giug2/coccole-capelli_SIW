package it.uniroma3.siw.coccolecapelli.controller;

import static it.uniroma3.siw.coccolecapelli.model.Parrucchiere.DIR_ADMIN_PAGES_PARR;
import static it.uniroma3.siw.coccolecapelli.model.Parrucchiere.DIR_FOLDER_IMG;
import static it.uniroma3.siw.coccolecapelli.model.Parrucchiere.DIR_PAGES_PARR;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import it.uniroma3.siw.coccolecapelli.controller.validator.ParrucchiereValidator;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.service.ParrucchiereService;
import it.uniroma3.siw.coccolecapelli.utility.FileStore;

@Controller
public class ParrucchiereController {
	
	@Autowired
	private ParrucchiereService parrucchiereService;
	
	@Autowired
	private ParrucchiereValidator parrucchiereValidator;
	
	
	/* METHODS GENERIC_USER */
	
	@GetMapping("/parrucchiere/{id}")
	public String getParrucchiere(@PathVariable("id") Long id, Model model) {
		Parrucchiere parrucchiere = parrucchiereService.findById(id);
		model.addAttribute("parrucchiere", parrucchiere);
		
		return DIR_PAGES_PARR + "parrucchiere";
	}
	
	@GetMapping("/parrucchieri")
	public String getParrucchieri(Model model) {
		model.addAttribute("parrucchieri", this.parrucchiereService.findAll());
		
		return DIR_PAGES_PARR + "elencoParrucchieri";
	}
	
	/* METHODS ADMIN */
	
	@GetMapping("/admin/parrucchiere/{id}")
	public String getAdminParrucchiere(@PathVariable("id") Long id, Model model) {
		Parrucchiere parrucchiere = parrucchiereService.findById(id);
		model.addAttribute("parrucchiere", parrucchiere);
		
		return DIR_ADMIN_PAGES_PARR + "adminParrucchiere";
	}
	
	@GetMapping("/admin/parrucchieri")
	public String getAdminParrucchieri(Model model) {
		model.addAttribute("parrucchieri", this.parrucchiereService.findAll());
		
		return DIR_ADMIN_PAGES_PARR + "adminElencoParrucchieri";
	}
	
	// --- INSERIMENTO
	
	@GetMapping("/admin/parrucchiere/add")
	public String addParrucchiere(Model model) {
		model.addAttribute("parrucchiere", new Parrucchiere());
		
		return DIR_ADMIN_PAGES_PARR + "parrucchiereForm";
	}
	
	@PostMapping("/admin/parrucchiere/add")
	public String addNewParrucchiere(@Valid @ModelAttribute("parrucchiere") Parrucchiere parrucchiere, 
									   BindingResult bindingResult, 
									   @RequestParam("file") MultipartFile file,
									   Model model) {
		this.parrucchiereValidator.validate(parrucchiere, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			parrucchiere.setImg(FileStore.store(file, DIR_FOLDER_IMG));
			this.parrucchiereService.save(parrucchiere);
			
			return this.getAdminParrucchieri(model);
		}
		
		return DIR_ADMIN_PAGES_PARR + "parrucchiereForm";
	}
	
	
	// --- ELIMINAZIONE
	
	/* root permesso / entita / azione / parametri */
	@GetMapping("/admin/parruchiere/delete/{id}")
	public String deleteParrucchiere(@PathVariable("id") Long id, Model model) {
		Parrucchiere parrucchiere = parrucchiereService.findById(id);
		FileStore.removeImg(DIR_FOLDER_IMG, parrucchiere.getImg());
		
		this.parrucchiereService.delete(parrucchiere);
		
		return this.getAdminParrucchieri(model);
	}
	
	// --- MODIFICA
	
	@GetMapping("/admin/parrucchiere/edit/{id}")
	public String getEditParrucchiere(@PathVariable("id") Long id, Model model) {
		model.addAttribute("parrucchiere",this.parrucchiereService.findById(id));	
		return DIR_ADMIN_PAGES_PARR + "editParrucchiere";
	}
	
	@PostMapping("/admin/parrucchiere/edit/{id}")
	public String modificaParrucchiere(@Valid @ModelAttribute("parrucchiere") Parrucchiere parrucchiere, 
							   BindingResult bindingResult, 
							   @PathVariable("id") Long id, 
							   Model model) {
		
		Parrucchiere p = this.parrucchiereService.findById(id);
		if(parrucchiere.getPartitaIVA().equals(p.getPartitaIVA())) {
			parrucchiere.setPartitaIVA("DefPartIVA");
			this.parrucchiereValidator.validate(parrucchiere, bindingResult);
			parrucchiere.setPartitaIVA(p.getPartitaIVA());
		}else {
			this.parrucchiereValidator.validate(parrucchiere, bindingResult);
		}
		
		parrucchiere.setId(id);
		if(!bindingResult.hasErrors()) {
			this.parrucchiereService.update(parrucchiere, parrucchiere.getId());
			return this.getAdminParrucchieri(model);
		}
		parrucchiere.setImg(p.getImg());
		return  DIR_ADMIN_PAGES_PARR + "editParrucchiere";
	}
	
	@PostMapping("/admin/parrucchiere/changeImg/{idParr}")
	public String changeImgParr(@PathVariable("idParr") Long idParr,
			   					@RequestParam("file") MultipartFile file, 
			   					Model model) {
		
		Parrucchiere p = this.parrucchiereService.findById(idParr);
		if(!p.getImg().equals("profili")) {
			FileStore.removeImg(DIR_FOLDER_IMG, p.getImg());
		}

		p.setImg(FileStore.store(file, DIR_FOLDER_IMG));
		this.parrucchiereService.save(p);
		return this.getEditParrucchiere(idParr, model);
	}
	
}
