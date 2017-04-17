package business.entity;

import java.time.LocalDate;

public abstract class Utente extends Entity {
	private int idUtente;
	private String Nome;
	private String Cognome;
	private String Sesso;
	private LocalDate DataNascita;
	private String Indirizzo;
	private String CodiceFiscale;
	private String NumCell;
	private String NumFisso;
	private boolean Assunto=true;
	
	
	public Utente(){
		
	}

	public Utente(int idUtente, String nome, String cognome, String sesso,
			LocalDate dataNascita, String indirizzo, String codiceFiscale,
			String numCell, String numFisso, boolean assunto) {
		super();
		this.idUtente = idUtente;
		Nome = nome;
		Cognome = cognome;
		Sesso = sesso;
		DataNascita = dataNascita;
		Indirizzo = indirizzo;
		CodiceFiscale = codiceFiscale;
		NumCell = numCell;
		NumFisso = numFisso;
		Assunto = assunto;
	}
	public int getIdUtente() {
		return idUtente;
	}
	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}
	public String getNome() {
		return Nome;
	}
	public void setNome(String nome) {
		Nome = nome;
	}
	public String getCognome() {
		return Cognome;
	}
	public void setCognome(String cognome) {
		Cognome = cognome;
	}
	public String getSesso() {
		return Sesso;
	}
	public void setSesso(String sesso) {
		Sesso = sesso;
	}
	public LocalDate getDataNascita() {
		return DataNascita;
	}
	public void setDataNascita(LocalDate dataNascita) {
		DataNascita = dataNascita;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getCodiceFiscale() {
		return CodiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		CodiceFiscale = codiceFiscale;
	}
	public String getNumCell() {
		return NumCell;
	}
	public void setNumCell(String numCell) {
		NumCell = numCell;
	}
	public String getNumFisso() {
		return NumFisso;
	}
	public void setNumFisso(String numFisso) {
		NumFisso = numFisso;
	}
	public boolean isAssunto() {
		return Assunto;
	}
	public void setAssunto(boolean assunto) {
		Assunto = assunto;
	}

}
