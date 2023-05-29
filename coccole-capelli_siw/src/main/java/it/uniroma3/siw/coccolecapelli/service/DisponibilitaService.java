package it.uniroma3.siw.coccolecapelli.service;

import java.util.List;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.coccolecapelli.model.Disponibilita;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;
import it.uniroma3.siw.coccolecapelli.repository.DisponibilitaRepository;

@Service
public class DisponibilitaService {

	@Autowired
	private DisponibilitaRepository disponibilitaRepository;
	
	public boolean alreadyExists(Disponibilita target) {
		return this.disponibilitaRepository.existsByDataAndOraInizioAndOraFineAndParrucchiere(target.getData(), target.getOraInizio(), target.getOraFine(), target.getParrucchiere());
	}

	public Disponibilita findById(Long idDisponibilita) {
		return this.disponibilitaRepository.findById(idDisponibilita).get();
	}
	
	public List<Disponibilita> findByParrAndActive(Parrucchiere parrucchiere) {
		return this.disponibilitaRepository.findByParrucchiereAndActiveTrueOrderByDataAscOraInizio(parrucchiere);
	}
	
	public List<Disponibilita> findByParrucchiere(Parrucchiere parrucchiere) {
		return this.disponibilitaRepository.findByParrucchiere(parrucchiere);
	}
	
	@Transactional
	public void update(Disponibilita disponibilita, Disponibilita newDisponibilita) {
		disponibilita.setData(newDisponibilita.getData());
		disponibilita.setOraInizio(newDisponibilita.getOraInizio());
		disponibilita.setOraFine(newDisponibilita.getOraFine());
		this.disponibilitaRepository.save(disponibilita);
	}
	
	@Transactional
	public void delete(Disponibilita disponibilita) {
		this.disponibilitaRepository.delete(disponibilita);
	}

}
