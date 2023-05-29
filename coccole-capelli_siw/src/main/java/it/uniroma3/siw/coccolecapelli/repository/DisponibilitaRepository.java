package it.uniroma3.siw.coccolecapelli.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.coccolecapelli.model.Disponibilita;
import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;

public interface DisponibilitaRepository extends CrudRepository<Disponibilita, Long> {

	public boolean existsByDataAndOraInizioAndOraFineAndParrucchiere(String data, String oraInizio, String oraFine, Parrucchiere parrucchiere);

	public List<Disponibilita> findByParrucchiere(Parrucchiere parrucchiere);
	
	public List<Disponibilita> findByParrucchiereAndActiveTrueOrderByDataAscOraInizio(Parrucchiere parrucchiere);

}
