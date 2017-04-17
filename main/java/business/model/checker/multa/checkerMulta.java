package business.model.checker.multa;

import business.entity.Entity;
import business.entity.Noleggio.Multa;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class checkerMulta implements Checker{
	private Multa multa;
	private boolean isValid;
	final static int MASSIMA_LUNGHEZZANOTE=100;
	

	final static float MINIMA_IMPORTO=0;

	final static float MASSIMA_IMPORTO=4000;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		multa=(Multa)entity;
		checkImporto();
		checkNote();
	}
	public void checkImporto() throws CommonException{
		float importo;
		importo= multa.getImporto();
		isValid = (importo>MINIMA_IMPORTO && importo<MASSIMA_IMPORTO);
		if (!isValid) {
			throw new CommonException("Importo troppo elevato");
		}
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
