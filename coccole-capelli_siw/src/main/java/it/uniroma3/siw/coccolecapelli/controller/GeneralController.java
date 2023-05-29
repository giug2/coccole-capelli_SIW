package it.uniroma3.siw.coccolecapelli.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.uniroma3.siw.coccolecapelli.service.ParrucchiereService;
@Controller
public class GeneralController {

	
	@Autowired
	private ParruchiereService parrucchiereService;

	@GetMapping("/")
	public String getServiziAndProfessionisti(Model model) {
		
		model.addAttribute("parrucchieri", this.parrucchiereService.findLastParrucchieri());
		
		return "index";
	}
	
	@GetMapping("/admin")
	public String get() {
		return "redirect:/admin/parrucchieri";
	}
	
}
