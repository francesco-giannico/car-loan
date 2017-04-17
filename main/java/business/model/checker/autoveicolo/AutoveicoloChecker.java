package business.model.checker.autoveicolo;

import java.time.LocalDate;

import business.entity.Entity;
import business.entity.Auto.Autoveicolo;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class AutoveicoloChecker implements Checker {
private Autoveicolo auto;
	@Override
	public void check(Entity entity) throws CommonException {
		auto=(Autoveicolo)entity;
		
		checkDate();
		checkInteri();
	}
	private void checkInteri() throws CommonException {
		if(auto.getCilindrata()<0)
			throw new CommonException("Cilindrata non valida");
		else if(auto.getPotenza()<0)
			throw new CommonException("Potenza non valida");
		else if(auto.getUltimoKm()<0)
				throw new CommonException("Kilometri non validi");
		else if(auto.getPrezzo()<0)
				throw new CommonException("Prezzo non valido");
			
			
	}
	private void checkDate() throws CommonException {
		LocalDate d=auto.getImmatricolazione();
		LocalDate d2=auto.getDataScadAssic();
		if(d==null )
			throw new CommonException("Data immatricolazione vuota");
		else if(d2==null)
				throw new CommonException("Data Scadenza assicurazione vuota");
		else if(d.getYear()<1807)
			throw new CommonException("Prima del 1807 non esistevano auto ");
		else if(!d.isBefore(LocalDate.now()))
			throw new CommonException("Immatricolazione non valida, è nel futuro!");
	}


}
