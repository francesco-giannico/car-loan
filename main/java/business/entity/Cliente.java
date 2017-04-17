package business.entity;
import java.time.LocalDate;


public class Cliente extends Entity{
	
	private Integer id;
	private String nome;
	private String cognome;
	private String sesso;
	private LocalDate DataEmissPatente;
	private	LocalDate datanascita;
	private String Indirizzo;
	private String codFiscale;
	private String numCell;
	private String numTel;
	private String PatenteGuida;
	private LocalDate DataScadPatente;
	private String PartitaIva;
	private String Email;
	public Cliente(){}
	
	public Cliente(Integer id, String nome, String cognome, String sesso,
			LocalDate dataEmissPatente, LocalDate datanascita,
			String indirizzo, String codFiscale, String numCell, String numTel,
			String patenteGuida, LocalDate dataScadPatente, String partitaIva,
			String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		DataEmissPatente = dataEmissPatente;
		this.datanascita = datanascita;
		Indirizzo = indirizzo;
		this.codFiscale = codFiscale;
		this.numCell = numCell;
		this.numTel = numTel;
		PatenteGuida = patenteGuida;
		DataScadPatente = dataScadPatente;
		PartitaIva = partitaIva;
		Email = email;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public LocalDate getDataEmissPatente() {
		return DataEmissPatente;
	}
	public void setDataEmissPatente(LocalDate dataEmissPatente) {
		DataEmissPatente = dataEmissPatente;
	}
	public LocalDate getDatanascita() {
		return datanascita;
	}
	public void setDatanascita(LocalDate datanascita) {
		this.datanascita = datanascita;
	}
	public String getIndirizzo() {
		return Indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		Indirizzo = indirizzo;
	}
	public String getCodFiscale() {
		return codFiscale;
	}
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}
	public String getNumCell() {
		return numCell;
	}
	public void setNumCell(String numCell) {
		this.numCell = numCell;
	}
	public String getNumTel() {
		return numTel;
	}
	public void setNumTel(String numTel) {
		this.numTel = numTel;
	}
	public String getPatenteGuida() {
		return PatenteGuida;
	}
	public void setPatenteGuida(String patenteGuida) {
		PatenteGuida = patenteGuida;
	}
	public LocalDate getDataScadPatente() {
		return DataScadPatente;
	}
	public void setDataScadPatente(LocalDate dataScadPatente) {
		DataScadPatente = dataScadPatente;
	}
	public String getPartitaIva() {
		return PartitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		PartitaIva = partitaIva;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	
	
	

}
