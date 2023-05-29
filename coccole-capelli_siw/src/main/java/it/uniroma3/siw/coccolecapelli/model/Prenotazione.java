package it.uniroma3.siw.coccolecapelli.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Prenotazione {
	
	public static final String DIR_PAGES_PREN = "information/prenotazione/";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Parrucchiere parrucchiere;
	
	@OneToOne
	private Disponibilita disponibilita;
	
	@ManyToOne
	private User cliente;
	
	@ManyToOne
	private Servizio servizio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Parrucchiere getParrucchiere() {
		return parrucchiere;
	}

	public void setParrucchiere(Parrucchiere parrucchiere) {
		this.parrucchiere = parrucchiere;
	}
	
	public Servizio getServizio() {
		return servizio;
	}

	public void setServizio(Servizio servizio) {
		this.servizio = servizio;
	}


	public Disponibilita getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(Disponibilita disponibilita) {
		this.disponibilita = disponibilita;
	}

	public User getCliente() {
		return cliente;
	}

	public void setCliente(User cliente) {
		this.cliente = cliente;
	}

}
