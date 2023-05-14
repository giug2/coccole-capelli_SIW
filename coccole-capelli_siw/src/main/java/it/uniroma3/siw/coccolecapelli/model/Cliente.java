package it.uniroma3.siw.coccolecapelli.model;

import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;
	private String cognome;
	private String numTelefono;
	
	@OneToMany(mappedBy="cliente")
	private List<Prenotazione> prenotazione;
	
	public Cliente (Long id, String nome, String cognome, String numero, List<Prenotazione> pren) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.numTelefono = numero;
		this.prenotazione = pren;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public List<Prenotazione> getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(List<Prenotazione> prenotazione) {
		this.prenotazione = prenotazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(nome, other.nome);
	}
	
}
