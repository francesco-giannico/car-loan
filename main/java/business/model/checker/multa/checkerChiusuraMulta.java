package business.model.checker.multa;

import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class checkerChiusuraMulta implements Checker{
	private Multa multa;
	private boolean isValid;
	final static int MASSIMA_LUNGHEZZANOTE=100;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		multa=(Multa)entity;
		checkNote();
	}

	
	public void checkNote() throws CommonException{
		int length=0;
		length= multa.getNote().length();
	    isValid = (length<MASSIMA_LUNGHEZZANOTE);
	 
	    if (!isValid) {
	    	throw new CommonException("Hai immesso troppi caratteri per le note");
	    }
	}
}
