package business.entity.Auto.manutenzione;

import java.time.LocalDate;

import business.entity.Entity;
public abstract class Manutenzione extends Entity{
	
	private int IDManutenzione;
	private LocalDate Datainizio;
	private LocalDate DataFine;
	private String note;
	private int IDAuto;
	public Manutenzione(int iDManutenzione, LocalDate localDate, LocalDate dataFine,
			String note,int i) {
		setIDManutenzione(iDManutenzione);
		Datainizio = localDate;
		DataFine = dataFine;
		this.note = note;
		this.IDAuto=i;
	}
	public Manutenzione(int iDManutenzione, LocalDate localDate, 
			String note,int i) {
		setIDManutenzione(iDManutenzione);
		Datainizio = localDate;
		this.note = note;
		this.IDAuto=i;
	}
	public Manutenzione( LocalDate localDate, LocalDate dataFine,
			String note,int i){
		Datainizio = localDate;
		DataFine = dataFine;
		this.note = note;
		this.IDAuto=i;
	}
	public LocalDate getDatainizio() {
		return Datainizio;
	}
	public void setDatainizio(LocalDate datainizio) {
		Datainizio = datainizio;
	}
	public LocalDate getDataFine() {
		return DataFine;
	}
	public void setDataFine(LocalDate dataFine) {
		DataFine = dataFine;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getIDAuto() {
		return IDAuto;
	}
	public void setIDAuto(int iDAuto) {
		IDAuto = iDAuto;
	}

	public int getIDManutenzione() {
		return IDManutenzione;
	}

	public void setIDManutenzione(int iDManutenzione) {
		IDManutenzione = iDManutenzione;
	}
}
