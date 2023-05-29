package it.uniroma3.siw.coccolecapelli.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.siw.coccolecapelli.model.Parrucchiere;

public interface ParrucchiereRepository extends CrudRepository<Parrucchiere, Long> {

	boolean existsByPartitaIVA(String partitaIVA);

	@Query(value = "SELECT * FROM parrucchiere order by id limit :limit", nativeQuery = true)
	public List<Parrucchiere> findTopN(@Param("limit") int limit);

}
