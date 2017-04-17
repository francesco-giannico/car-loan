package business.entity.Gestori;

import java.time.LocalDate;

import business.entity.Utente;

public abstract class Supervisore extends Utente{

	public Supervisore(int idUtente, String nome, String cognome,
			String sesso, LocalDate dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
	}

	public Supervisore() {
		// TODO Auto-generated constructor stub
	}


}
