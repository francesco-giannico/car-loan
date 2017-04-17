package business.model.checker.autoveicolo;

import java.time.LocalDate;

import business.entity.Entity;
import business.entity.Auto.manutenzione.Manutenzione;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class ManutenzioneChecker implements Checker{

	@Override
	public void check(Entity entity) throws CommonException {
		Manutenzione a=(Manutenzione)entity;
		if(a.getDatainizio().isBefore(LocalDate.now()))
			throw new CommonException("La data di inizio è passata");
		
	}

}
