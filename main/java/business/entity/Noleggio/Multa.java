package business.entity.Noleggio;

import java.time.LocalDate;

import business.entity.Entity;

public class Multa extends Entity{
	
	private int IDMulta;
	private LocalDate DataMulta;
	private float importo;
	private StatoMulta stato;
	private String Note;
	private int idNoleggio;
	private LocalDate DataScadenza;
	private float ulterioreAddebito;
	private LocalDate dataPagamento;
	public Multa(){}
	public LocalDate getDataScadenza() {
		return DataScadenza;
	}
	public void setDataScadenza(LocalDate dataScadenza) {
		DataScadenza = dataScadenza;
	}
	public float getUlterioreAddebito() {
		return ulterioreAddebito;
	}
	public void setUlterioreAddebito(float ulterioreAddebito) {
		this.ulterioreAddebito = ulterioreAddebito;
	}
	public LocalDate getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public int getIdNoleggio() {
		return idNoleggio;
	}
	public void setIdNoleggio(int idNoleggio) {
		this.idNoleggio = idNoleggio;
	}

	public int getIDMulta() {
		return IDMulta;
	}
	public void setIDMulta(int iDMulta) {
		IDMulta = iDMulta;
	}
	public LocalDate getDataMulta() {
		return DataMulta;
	}
	public void setDataMulta(LocalDate dataMulta) {
		DataMulta = dataMulta;
	}
	public float getImporto() {
		return importo;
	}
	public void setImporto(float importo) {
		this.importo = importo;
	}
	public StatoMulta getStato() {
		return stato;
	}
	public void setStato(StatoMulta stato) {
		this.stato = stato;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public Multa(int iDMulta, LocalDate dataMulta, float importo,
			StatoMulta stato, String note, int idNoleggio,
			LocalDate dataScadenza, float ulterioreAddebito,
			LocalDate dataPagamento) {
		super();
		IDMulta = iDMulta;
		DataMulta = dataMulta;
		this.importo = importo;
		this.stato = stato;
		Note = note;
		this.idNoleggio = idNoleggio;
		DataScadenza = dataScadenza;
		this.ulterioreAddebito = ulterioreAddebito;
		this.dataPagamento = dataPagamento;
	}

	
	

}
