package it.uniroma3.siw.coccolecapelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.coccolecapelli.service.ServizioService;

import it.uniroma3.siw.coccolecapelli.service.ParrucchiereService;
@Controller
public class GeneralController {


	@Autowired
	private ServizioService servizioService;
	
	@Autowired
	private ParrucchiereService parrucchiereService;

	@GetMapping("/")
	public String getServiziAndParrucchieri(Model model) {
		
		model.addAttribute("parrucchieri", this.parrucchiereService.findLastParrucchieri());
		model.addAttribute("servizi", this.servizioService.findLastServizi());
		
		return "index";
	}
	
	@GetMapping("/admin")
	public String get() {
		return "redirect:/admin/parrucchieri";
	}
	
}
