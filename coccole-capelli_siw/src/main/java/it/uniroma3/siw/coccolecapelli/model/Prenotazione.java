package it.uniroma3.siw.coccolecapelli.model;

import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Prenotazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Dipendente dipendente;
	
	@OneToOne
	private Disponibilita disponibilita;
	
	public Prenotazione (Long id, Cliente cliente, Disponibilita disp, Dipendente dipen) {
		this.id = id;
		this.cliente = cliente;
		this.dipendente = dipen;
		this.disponibilita = disp;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Disponibilita getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(Disponibilita disponibilita) {
		this.disponibilita = disponibilita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dipendente, disponibilita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return Objects.equals(dipendente, other.dipendente) && Objects.equals(disponibilita, other.disponibilita);
	}
	
}
