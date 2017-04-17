package business.model.checker.noleggio;

import business.entity.Entity;
import business.entity.Noleggio.Noleggio;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class checkerNoleggio implements Checker{
	private Noleggio noleggio;
	private boolean isValid;
	final static int MASSIMA_LUNGHEZZANOTE=200;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		noleggio=(Noleggio)entity;
		checkNote();
	}
	public void checkNote() throws CommonException{
		int length=0;
		length= noleggio.getNote().length();
	    isValid = (length<MASSIMA_LUNGHEZZANOTE);
	 
	    if (!isValid) {
	    	throw new CommonException("Hai immesso troppi caratteri per le note");
	    }
	}
}
