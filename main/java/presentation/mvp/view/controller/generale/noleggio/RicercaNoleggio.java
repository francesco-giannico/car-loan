package presentation.mvp.view.controller.generale.noleggio;

import java.time.LocalDate;

import business.entity.Entity;
import business.entity.Noleggio.StatoNoleggio;

public class RicercaNoleggio extends Entity{
	private StatoNoleggio stato;
	private LocalDate dInizio;
	private int idContratto;
	
	public RicercaNoleggio(StatoNoleggio stato, LocalDate dInizio,
			int txtContratto) {
		super();
		this.stato = stato;
		this.dInizio = dInizio;
		this.idContratto = txtContratto;
	}

	public StatoNoleggio getStato() {
		return stato;
	}

	public void setStato(StatoNoleggio stato) {
		this.stato = stato;
	}

	public LocalDate getdInizio() {
		return dInizio;
	}

	public void setdInizio(LocalDate dInizio) {
		this.dInizio = dInizio;
	}

	public int getidContratto() {
		return idContratto;
	}

	public void setidContratto(int txtContratto) {
		this.idContratto = txtContratto;
	}

	
	
	
}
