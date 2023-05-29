package it.uniroma3.siw.coccolecapelli.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.coccolecapelli.model.Servizio;
import it.uniroma3.siw.coccolecapelli.model.Disponibilita;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.repository.ParrucchiereRepository;

@Service
public class ParrucchiereService {

	@Autowired
	private ParrucchiereRepository parrucchiereRepository;
	
	public boolean alreadyExists(Parrucchiere target) {
		return parrucchiereRepository.existsByPartitaIVA(target.getPartitaIVA());
	}

	public Parrucchiere findById(Long id) {
		return parrucchiereRepository.findById(id).get();
	}

	@Transactional
	public void save(Parrucchiere parrucchiere) {
		parrucchiereRepository.save(parrucchiere);
	}
	
	public List<Parrucchiere> findAll() {
		return (List<Parrucchiere>) parrucchiereRepository.findAll();
	}
	
	
	@Transactional
	public void delete(Parrucchiere parrucchiere) {
		this.parrucchiereRepository.delete(parrucchiere);
	}
	
	
	@Transactional
	public void update(Parrucchiere parrucchiere, Long id) {
		Parrucchiere p = this.parrucchiereRepository.findById(id).get();
		p.setNome(parrucchiere.getNome());
		p.setCognome(parrucchiere.getCognome());
		p.setSpecializzazione(parrucchiere.getSpecializzazione());
		p.setPartitaIVA(parrucchiere.getPartitaIVA());
		this.parrucchiereRepository.save(p);
	}
	
	
	@Transactional
	public void addServizio(Parrucchiere parrucchiere, Servizio servizio) {
		parrucchiere.getServizi().add(servizio);
		this.parrucchiereRepository.save(parrucchiere);
	}
	
	@Transactional
	public void addDisponibilita(Parrucchiere parrucchiere, Disponibilita disponibilita) {
		parrucchiere.getDisponibilita().add(disponibilita);
		this.parrucchiereRepository.save(parrucchiere);
	}


	public List<Parrucchiere> findLastParrucchieri() {
		return this.parrucchiereRepository.findTopN(6);
	}

}
