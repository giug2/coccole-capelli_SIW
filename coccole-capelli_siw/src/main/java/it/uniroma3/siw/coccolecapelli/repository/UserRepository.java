package it.uniroma3.siw.coccolecapelli.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.coccolecapelli.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}