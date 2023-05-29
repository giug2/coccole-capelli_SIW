package it.uniroma3.siw.coccolecapelli.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

import it.uniroma3.siw.coccolecapelli.utility.FileStore;

@Entity
public class Servizio {
	
	public static final String DIR_PAGES_SERVIZIO = "information/servizio/";
	public static final String DIR_ADMIN_PAGES_SERVIZIO = "admin/servizio/";
	
	public static final String DIR_FOLDER_IMG = "servizio/profili";
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descrizione;
	
	/*@NotBlank*/
	private Float prezzo;
	
	private String img;
	
	@ManyToOne
	private Parrucchiere parrucchiere;
	
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

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Float getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Float prezzo) {
		this.prezzo = prezzo;
	}

	public Parrucchiere getParrucchiere() {
		return parrucchiere;
	}

	public void setParrucchiere(Parrucchiere parrucchiere) {
		this.parrucchiere = parrucchiere;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	public void eliminaImmagine() {
		FileStore.removeImg(DIR_FOLDER_IMG, this.getImg());
	}
	
}
