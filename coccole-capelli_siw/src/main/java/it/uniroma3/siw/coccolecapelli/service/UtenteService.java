package it.uniroma3.siw.coccolecapelli.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.uniroma3.siw.coccolecapelli.model.Prenotazione;
import it.uniroma3.siw.coccolecapelli.model.User;
import it.uniroma3.siw.coccolecapelli.repository.UtenteRepository;

@Service
public class UtenteService {
	
	@Autowired
	private UtenteRepository utenteRepository;
	
    @Transactional
    public User save(User utente) {
        return this.utenteRepository.save(utente);
    }
    
    public User getUser(Long id) {
        Optional<User> result = this.utenteRepository.findById(id);
        return result.orElse(null);
    }
	
    public List<User> getAllUsers() {
        List<User> result = new ArrayList<>();
        Iterable<User> iterable = this.utenteRepository.findAll();
        for(User user : iterable)
            result.add(user);
        return result;
    }
    
	public boolean alreadyExists(User u) {
		return utenteRepository.existsByNomeAndCognome(u.getNome(), u.getCognome());
	}
	
	@Transactional
	public void addPrenotazione(User u, Prenotazione prenotazione) {
		u.getPrenotazioni().add(prenotazione);
		this.utenteRepository.save(u);
	}
	
	@Transactional
	public void deletePrenotazione(User u, Prenotazione prenotazione) {
		u.getPrenotazioni().remove(prenotazione);
		this.utenteRepository.save(u);
	}
}
