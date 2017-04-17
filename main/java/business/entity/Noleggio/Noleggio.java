package business.entity.Noleggio;

import java.time.LocalDate;
import java.util.List;

import business.entity.Entity;

public class Noleggio extends Entity{
	
	private int IDNoleggio; 
	private LocalDate InizioNoleggio;
	private LocalDate FineNoleggio;
	private LocalDate rientro;
	private LocalDate ritiro;
	private int kmRientro;
	private int KmPercorsi;
	private int kmBase;
	private List<Integer> optional;
	private List<Integer> multa;
	private int sedeRestituzione;
	private int numeroSettimane;
	private int numeroGiorni;
	private int numeroChilometri;
	private StatoNoleggio stato;
	private int idAuto;
	private int Idcontratto;
	private String note;
	private int idPagamento;
	
	
	public Noleggio(){}
	public Noleggio(int iDNoleggio, LocalDate inizioNoleggio,
			LocalDate fineNoleggio, LocalDate rientro, LocalDate ritiro,
			int kmRientro,  int kmBase, List<Integer> optional,
			List<Integer> multa,StatoNoleggio stato,  int numeroSettimane,
			int numeroGiorni, int numeroChilometri,int sedeRestituzione,int idcontratto,
			int idPagamento,int idAuto,  String note) {
		super();
		IDNoleggio = iDNoleggio;
		InizioNoleggio = inizioNoleggio;
		FineNoleggio = fineNoleggio;
		this.rientro = rientro;
		this.ritiro = ritiro;
		this.kmRientro = kmRientro;
		this.multa= multa;
		this.optional=optional;
		this.kmBase = kmBase;
		this.optional = optional;
		this.multa = multa;
		this.sedeRestituzione = sedeRestituzione;
		this.numeroSettimane = numeroSettimane;
		this.numeroGiorni = numeroGiorni;
		this.numeroChilometri = numeroChilometri;
		this.stato = stato;
		this.idAuto = idAuto;
		Idcontratto = idcontratto;
		this.note = note;
		this.idPagamento = idPagamento;
	}
	
	public int getIDNoleggio() {
		return IDNoleggio;
	}
	public void setIDNoleggio(int iDNoleggio) {
		IDNoleggio = iDNoleggio;
	}
	public LocalDate getInizioNoleggio() {
		return InizioNoleggio;
	}
	public void setInizioNoleggio(LocalDate inizioNoleggio) {
		InizioNoleggio = inizioNoleggio;
	}
	public LocalDate getFineNoleggio() {
		return FineNoleggio;
	}
	public void setFineNoleggio(LocalDate fineNoleggio) {
		FineNoleggio = fineNoleggio;
	}
	public LocalDate getRientro() {
		return rientro;
	}
	public void setRientro(LocalDate rientro) {
		this.rientro = rientro;
	}
	public LocalDate getRitiro() {
		return ritiro;
	}
	public void setRitiro(LocalDate ritiro) {
		this.ritiro = ritiro;
	}
	public int getKmRientro() {
		return kmRientro;
	}
	public void setKmRientro(int kmRientro) {
		this.kmRientro = kmRientro;
	}
	public int getKmPercorsi() {
		return KmPercorsi;
	}
	public void setKmPercorsi(int kmPercorsi) {
		KmPercorsi = kmPercorsi;
	}
	public int getKmBase() {
		return kmBase;
	}
	public void setKmBase(int kmBase) {
		this.kmBase = kmBase;
	}
	public List<Integer> getOptional() {
		return optional;
	}
	public void setOptional(List<Integer> optional) {
		this.optional = optional;
	}
	public List<Integer> getMulta() {
		return multa;
	}
	public void setMulta(List<Integer> multa) {
		this.multa = multa;
	}
	public int getSedeRestituzione() {
		return sedeRestituzione;
	}
	public void setSedeRestituzione(int sedeRestituzione) {
		this.sedeRestituzione = sedeRestituzione;
	}
	public int getNumeroSettimane() {
		return numeroSettimane;
	}
	public void setNumeroSettimane(int numeroSettimane) {
		this.numeroSettimane = numeroSettimane;
	}
	public int getNumeroGiorni() {
		return numeroGiorni;
	}
	public void setNumeroGiorni(int numeroGiorni) {
		this.numeroGiorni = numeroGiorni;
	}
	public int getNumeroChilometri() {
		return numeroChilometri;
	}
	public void setNumeroChilometri(int numeroChilometri) {
		this.numeroChilometri = numeroChilometri;
	}
	public StatoNoleggio getStato() {
		return stato;
	}
	public void setStato(StatoNoleggio stato) {
		this.stato = stato;
	}
	public int getIdAuto() {
		return idAuto;
	}
	public void setIdAuto(int idAuto) {
		this.idAuto = idAuto;
	}
	public int getIdcontratto() {
		return Idcontratto;
	}
	public void setIdcontratto(int idcontratto) {
		Idcontratto = idcontratto;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getIdPagamento() {
		return idPagamento;
	}
	public void setIdPagamento(int idPagamento) {
		this.idPagamento = idPagamento;
	}
}
