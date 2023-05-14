package it.uniroma3.siw.coccolecapelli.model;

import java.util.List;
import java.util.Objects;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Dipendente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String nome;
	private String numTelefono;
	
	@Nonnull
	private String specializzazione;
	
	@OneToMany(mappedBy="dipendente")
	private List<Disponibilita> disponibilita;
	
	@OneToMany(mappedBy="dipendente")
	private List<Prenotazione> prenotazione;
	
	public Dipendente(Long id, String nome, String numero, String spec, List<Disponibilita> disp) {
		this.id = id;
		this.nome = nome;
		this.numTelefono = numero;
		this.specializzazione = spec;
		this.disponibilita = disp;
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

	public String getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(String numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getSpecializzazione() {
		return specializzazione;
	}

	public void setSpecializzazione(String specializzazione) {
		this.specializzazione = specializzazione;
	}

	public List<Disponibilita> getDisponibilità() {
		return disponibilita;
	}

	public void setDisponibilità(List<Disponibilita> disponibilità) {
		this.disponibilita = disponibilità;
	}
	
	public List<Disponibilita> getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(List<Disponibilita> disponibilita) {
		this.disponibilita = disponibilita;
	}

	public List<Prenotazione> getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(List<Prenotazione> prenotazione) {
		this.prenotazione = prenotazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, specializzazione);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(specializzazione, other.specializzazione);
	}

}
