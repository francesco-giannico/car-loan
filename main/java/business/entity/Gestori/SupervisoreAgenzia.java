package business.entity.Gestori;

import java.time.LocalDate;

public class SupervisoreAgenzia extends Supervisore {

	private int IDAgenzia;

	public SupervisoreAgenzia(int idUtente, String nome, String cognome,
			String sesso, LocalDate dataNascita, String indirizzo,
			String codiceFiscale, String numCell, String numFisso,
			boolean assunto,int IDAgenzia) {
		super(idUtente, nome, cognome, sesso, dataNascita, indirizzo, codiceFiscale,
				numCell, numFisso, assunto);
		// TODO Auto-generated constructor stub
		this.IDAgenzia=IDAgenzia;
	}
	public SupervisoreAgenzia() {
		// TODO Auto-generated constructor stub
	}
	public int getIDAgenzia() {
		return IDAgenzia;
	}
	public void setIDAgenzia(int iDAgenzia) {
		IDAgenzia = iDAgenzia;
	}
	

}

