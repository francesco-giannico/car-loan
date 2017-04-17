package business.entity.Noleggio;


import java.time.LocalDate;
import business.entity.Entity;

public class Contratto extends Entity{

	private int IDContratto;
	private Integer IDOperatore;
	private Integer IDSupervisoreSede;
	private Integer IDSupervisoreAgenzia;
	private Integer IDAmministratore;
	private StatoContratto stato; /// questo può esssere enumerativo di regola
	private LocalDate DataCreazione;
	private LocalDate dataChiusura;
	private String Note;
	private int idCliente;
	
	public int getIDContratto() {
		return IDContratto;
	}
	public void setIDContratto(Integer iDContratto) {
		IDContratto = iDContratto;
	}
	public Integer getIDOperatore() {
		return IDOperatore;
	}
	public void setIDOperatore(Integer iDOperatore) {
		IDOperatore = iDOperatore;
	}
	public Integer getIDSupervisoreSede() {
		return IDSupervisoreSede;
	}
	public void setIDSupervisoreSede(Integer iDSupervisoreSede) {
		IDSupervisoreSede = iDSupervisoreSede;
	}
	public Integer getIDSupervisoreAgenzia() {
		return IDSupervisoreAgenzia;
	}
	public void setIDSupervisoreAgenzia(Integer iDSupervisoreAgenzia) {
		IDSupervisoreAgenzia = iDSupervisoreAgenzia;
	}
	public Integer getIDAmministratore() {
		return IDAmministratore;
	}
	public void setIDAmministratore(Integer iDAmministratore) {
		IDAmministratore = iDAmministratore;
	}

	public StatoContratto getStato() {
		return stato;
	}
	public void setStato(StatoContratto stato) {
		this.stato = stato;
	}
	public void setIDContratto(int iDContratto) {
		IDContratto = iDContratto;
	}
	public LocalDate getDataCreazione() {
		return DataCreazione;
	}
	public void setDataCreazione(LocalDate dataCreazione) {
		DataCreazione = dataCreazione;
	}
	public LocalDate getDataChiusura() {
		return dataChiusura;
	}
	public void setDataChiusura(LocalDate dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	public String getNote() {
		return Note;
	}
	public void setNote(String note) {
		Note = note;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	
	
	
}


