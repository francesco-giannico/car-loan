package business.model.checker.guidatore;

import business.entity.Entity;
import business.entity.Noleggio.Optional.Guidatore;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class GuidatoreChecker implements Checker{
	private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;
    
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 20;
    
    private static final int MIN_INDIRIZZO_VALUE=10;
    private static final int MAX_INDIRIZZO_VALUE=50;
    
    private static final int CODFISCALE_VALUE= 16;
    private static final int NUMPATENTE_VALUE= 10;
	private Guidatore guidatore;
	private boolean isValid;
	@Override
	public void check(Entity entity) throws CommonException {
		// TODO Auto-generated method stub
		guidatore= (Guidatore)entity;
		checkNome();
		checkCognome();
		checkIndirizzo();
		checkCodFiscale();
		checkPatente();
		
	}
	private void checkPatente() throws CommonException{
		int length;
		
        length = guidatore.getPatenteGuida().length();

        isValid = (length==NUMPATENTE_VALUE);
     

        if (!isValid) {
        	throw new CommonException("Patente non valida");
        }
	}
	private void checkIndirizzo() throws CommonException{
		int length;
		
        length = guidatore.getIndirizzo().length();

        isValid = (length >= MIN_INDIRIZZO_VALUE)
                && (length <= MAX_INDIRIZZO_VALUE);

        if (!isValid) {
        	throw new CommonException("Indirizzo  non valido");
        }
	}
	
	private void checkCodFiscale() throws CommonException{
		int length;
		
        length = guidatore.getCodFiscale().length();

        isValid = (length==CODFISCALE_VALUE);

        if (!isValid) {
        	throw new CommonException("Codice Fiscale non valido");
        }
	}
	private void checkNome() throws CommonException{
		int length;
		
        length = guidatore.getNome().length();

        isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);
        
        if (!isValid) {
        	throw new CommonException("Nome  non valido");
        }
	}
	
	
	private void checkCognome() throws CommonException{
		int length;
		
        length = guidatore.getCognome().length();

        isValid = (length >= MIN_COGNOME_VALUE)
                && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
        	throw new CommonException("Cognome  non valido");
        }
	}
	
}
