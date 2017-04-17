package business.entity.Noleggio.Optional;

import business.entity.Entity;

public class Guidatore extends Entity{
	private Integer id;
private String Nome;
private String Cognome;
private String Indirizzo;
private String CodFiscale;
private String PatenteGuida;
private int idNoleggio;

public Guidatore(Integer id,String nome, String cognome,
		String indirizzo, String codFiscale, String patenteGuida) {
	super();
	Nome = nome;
	Cognome = cognome;
	Indirizzo = indirizzo;
	CodFiscale = codFiscale;
	PatenteGuida = patenteGuida;
	this.id=id;
}

public Guidatore(String nome, String cognome,
		String indirizzo, String codFiscale, String patenteGuida, int idNoleggio) {
	super();
	Nome = nome;
	Cognome = cognome;
	Indirizzo = indirizzo;
	CodFiscale = codFiscale;
	PatenteGuida = patenteGuida;
	this.idNoleggio = idNoleggio;
}

public int getIdNoleggio() {
	return idNoleggio;
}

public void setIdNoleggio(int idNoleggio) {
	this.idNoleggio = idNoleggio;
}

public Guidatore(){
	


}
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
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

public String getIndirizzo() {
	return Indirizzo;
}
public void setIndirizzo(String indirizzo) {
	Indirizzo = indirizzo;
}
public String getCodFiscale() {
	return CodFiscale;
}
public void setCodFiscale(String codFiscale) {
	CodFiscale = codFiscale;
}
public String getPatenteGuida() {
	return PatenteGuida;
}
public void setPatenteGuida(String patenteGuida) {
	PatenteGuida = patenteGuida;
}

@Override
public int hashCode(){
	return CodFiscale.hashCode();
}
 
//diversi su patente e codice Fiscale
@Override
public boolean equals(Object x){
	if(x!=null && x instanceof Guidatore){
		if(this.getCodFiscale().equals(((Guidatore)x).getCodFiscale()) && this.getPatenteGuida().equals(((Guidatore)x).getPatenteGuida())){
    		return true;
    	}
	}
	return false;
}
}
