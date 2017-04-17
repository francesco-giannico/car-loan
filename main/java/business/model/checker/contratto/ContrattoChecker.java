package business.model.checker.contratto;

import business.entity.Entity;
import business.entity.Noleggio.Contratto;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class ContrattoChecker implements Checker{
	private static final int MAX_NOTE_VALUE = 200;
	private Contratto contratto;
	private boolean isValid;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		contratto= (Contratto)entity;
		checkNote();
		
	}
	public void checkNote() throws CommonException{
		int length;
		
        length = contratto.getNote().length();

        isValid = length==0 ||(length <= MAX_NOTE_VALUE) ;
        
        if (!isValid) {
        	throw new CommonException("Note non valide");
        }
	}
}
