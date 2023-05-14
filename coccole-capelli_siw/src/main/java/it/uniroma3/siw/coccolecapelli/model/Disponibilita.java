package it.uniroma3.siw.coccolecapelli.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;

@Entity
public class Disponibilita {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Nonnull
	private String orario;
	
	@Nonnull
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate data;
	
	@ManyToOne
	private Dipendente dipendente;
	
	@OneToOne 
	private Prenotazione prenotazione;
	
	public Disponibilita (Long id, String ora, LocalDate data,Dipendente dipendente) {
		this.id = id;
		this.orario = ora;
		this.data = data;
		this.dipendente = dipendente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}
	
	public Prenotazione getPrenotazione() {
		return prenotazione;
	}

	public void setPrenotazione(Prenotazione prenotazione) {
		this.prenotazione = prenotazione;
	}

	@Override
	public int hashCode() {
		return Objects.hash(data, orario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Disponibilita other = (Disponibilita) obj;
		return Objects.equals(data, other.data) && Objects.equals(orario, other.orario);
	}

}
