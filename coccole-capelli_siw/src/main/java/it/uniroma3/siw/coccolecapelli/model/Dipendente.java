package it.uniroma3.siw.coccolecapelli.model;


import jakarta.persistence.*;

@Entity
public class Dipendente {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String nome;
	private String numTelefono;
	private String specializzazione;
	
	
}
