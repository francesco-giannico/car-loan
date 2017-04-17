package business.entity.Auto;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;




import business.entity.Entity;
import business.entity.Auto.manutenzione.Manutenzione;
public class Autoveicolo extends Entity {
	
	private int IDauto;
	private String Targa;
	private String Marca;
	private String Modello;
	private String AlimPrincipale;// può essere un enum, ma comunque non potrebbero avere attributi propri un domani
	private String AlimSec;
	private String Colore;
	private String Cambio;
	private LocalDate Immatricolazione;
	private int Cilindrata;
	private int potenza;
	private int NroPosti;
	private String NroTelaio;
	private Disponibilita disponibilita;
	private int UltimoKm;
	private int CapPortaBagnagli;
	private String Note;
	private String Immagine;
	private InputStream immagine_stream;
	private LocalDate DataScadAssic;
	private int idfascia;
	private Danni danni;
	private int CodiceSedDisp;
	private String OptionalAuto;
	private List<Manutenzione> manutenzioni;
	private float prezzo;


	public Autoveicolo() {
	}


	public int getIDauto() {
		return IDauto;
	}


	public void setIDauto(int iDauto) {
		IDauto = iDauto;
	}


	public String getTarga() {
		return Targa;
	}


	public void setTarga(String targa) {
		Targa = targa;
		
	}


	public String getMarca() {
		return Marca;
	}


	public void setMarca(String marca) {
		Marca = marca;
	}


	public String getModello() {
		return Modello;
	}


	public void setModello(String modello) {
		Modello = modello;
	}


	public String getAlimPrincipale() {
		return AlimPrincipale;
	}


	public void setAlimPrincipale(String alimPrincipale) {
		AlimPrincipale = alimPrincipale;
	}


	public String getAlimSec() {
		return AlimSec;
	}


	public void setAlimSec(String alimSec) {
		AlimSec = alimSec;
	}


	public String getColore() {
		return Colore;
	}


	public void setColore(String colore) {
		Colore = colore;
	}


	public String getCambio() {
		return Cambio;
	}


	public void setCambio(String cambio) {
		Cambio = cambio;
	}


	public LocalDate getImmatricolazione() {
		return Immatricolazione;
	}


	public void setImmatricolazione(LocalDate immatricolazione) {
		Immatricolazione = immatricolazione;
	}


	public int getCilindrata() {
		return Cilindrata;
	}


	public void setCilindrata(int cilindrata) {
		Cilindrata = cilindrata;
	}


	public int getPotenza() {
		return potenza;
	}


	public void setPotenza(int potenza) {
		this.potenza = potenza;
	}


	public int getNroPosti() {
		return NroPosti;
	}


	public void setNroPosti(int nroPosti) {
		NroPosti = nroPosti;
	}


	public String getNroTelaio() {
		return NroTelaio;
	}


	public void setNroTelaio(String nroTelaio) {
		NroTelaio = nroTelaio;
	}


	public Disponibilita getDisponibilita() {
		return disponibilita;
	}


	public void setDisponibilita(Disponibilita disponibilita) {
		this.disponibilita = disponibilita;
	}


	public int getUltimoKm() {
		return UltimoKm;
	}


	public void setUltimoKm(int ultimoKm) {
		UltimoKm = ultimoKm;
	}


	public int getCapPortaBagnagli() {
		return CapPortaBagnagli;
	}


	public void setCapPortaBagnagli(int capPortaBagnagli) {
		CapPortaBagnagli = capPortaBagnagli;
	}


	public String getNote() {
		return Note;
	}


	public void setNote(String note) {
		Note = note;
	}


	public String getImmagine() {
		return Immagine;
	}


	public void setImmagine(String image) {
		Immagine = image.replace('\\', '/');
	}


	public LocalDate getDataScadAssic() {
		return DataScadAssic;
	}


	public void setDataScadAssic(LocalDate dataScadAssic) {
		DataScadAssic = dataScadAssic;
	}


	public int getFascia() {
		return idfascia;
	}


	public void setFascia(int fascia) {
		this.idfascia = fascia;
	}


	public Danni getDanni() {
		return danni;
	}


	public void setDanni(Danni danni) {
		this.danni = danni;
	}


	public int getCodiceSedDisp() {
		return CodiceSedDisp;
	}


	public void setCodiceSedDisp(int codiceSedDisp) {
		CodiceSedDisp = codiceSedDisp;
	}


	public String getOptionalAuto() {
		return OptionalAuto;
	}


	public void setOptionalAuto(String optionalAuto) {
		OptionalAuto = optionalAuto;
		
	}


	public List<Manutenzione> getManutenzioni() {
		return manutenzioni;
	}


	public void setManutenzioni(List<Manutenzione> manutenzioni) {
		this.manutenzioni = manutenzioni;
	}


	public float getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}


	public InputStream getImmagine_stream() {
		return immagine_stream;
	}


	public void setImmagine_stream(InputStream immagine_stream) {
		this.immagine_stream = immagine_stream;
	}


	
	
}
