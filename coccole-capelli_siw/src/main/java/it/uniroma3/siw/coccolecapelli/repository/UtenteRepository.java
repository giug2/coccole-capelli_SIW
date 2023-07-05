package it.uniroma3.siw.coccolecapelli.repository;

import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.coccolecapelli.model.User;

public interface UtenteRepository extends CrudRepository<User, Long> {

	boolean existsByNomeAndCognome(String nome, String cognome);

}
