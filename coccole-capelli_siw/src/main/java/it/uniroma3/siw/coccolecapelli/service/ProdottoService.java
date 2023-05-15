package it.uniroma3.siw.coccolecapelli.service;

import java.util.List;
import java.util.Optional;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.coccolecapelli.model.Prodotto;
import it.uniroma3.siw.coccolecapelli.repository.ProdottoRepository;

@Service
public class ProdottoService {
	
	@Autowired
	private ProdottoRepository prodottoRepository; 
	
	@Transactional
	public Prodotto inserisci(Prodotto prodotto) {
		return prodottoRepository.save(prodotto);
	}

	@Transactional
	public List<Prodotto> tutti() {
		return (List<Prodotto>) prodottoRepository.findAll();
	}

	@Transactional
	public Prodotto prodottoPerId(Long id) {
		Optional<Prodotto> optional = prodottoRepository.findById(id);
		if (optional.isPresent())
			return optional.get();
		else 
			return null;
	}

	@Transactional
	public boolean alreadyExists(Prodotto prodotto) {
		List<Prodotto> prodotti = this.prodottoRepository.findByNome(prodotto.getNome());
		if (prodotti.size() > 0)
			return true;
		else 
			return false;
	}
}
