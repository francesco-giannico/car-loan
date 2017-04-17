package business.entity.Gestori;

import java.time.LocalDate;
import business.entity.Utente;

public class Operatore extends Utente {
	private int IDSede;
	public Operatore(int idUtente, String nome, String cognome,
			String sesso, LocalDate dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto,int idSede) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
		this.IDSede=idSede;
	}
	public Operatore() {
		
	}
	public int getIDSede() {
		return IDSede;
	}
	public void setIDSede(int iDSede) {
		IDSede = iDSede;
	}



}

