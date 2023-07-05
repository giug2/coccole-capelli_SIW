package it.uniroma3.siw.coccolecapelli.service;

import java.util.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.coccolecapelli.model.Servizio;
import it.uniroma3.siw.coccolecapelli.repository.ServizioRepository;

@Service
public class ServizioService {

	@Autowired
	private ServizioRepository servizioRepository;
	
	@Transactional
	public void save(Servizio servizio) {
		this.servizioRepository.save(servizio);
	}
	
	public boolean alreadyExists(Servizio target) {
		return this.servizioRepository.existsByNomeAndDipendente(target.getNome(), target.getDipendente());
	}
	
	@Transactional
	public void delete(Servizio servizio) {
		this.servizioRepository.delete(servizio);
	}
	
	@Transactional
	public void update(Servizio servizio, Servizio newServizio) {
		servizio.setNome(newServizio.getNome());
		servizio.setDescrizione(newServizio.getDescrizione());
		servizio.setPrezzo(newServizio.getPrezzo());
		this.servizioRepository.save(servizio);
	}

	public Servizio findById(Long id) {
		return this.servizioRepository.findById(id).get();
	}

	public List<Servizio> findAll() {
		return (List<Servizio>) this.servizioRepository.findAll();
	}
	
	public List<Servizio> findLastServizi() {
		return this.servizioRepository.findTopN(6);
	}
}
