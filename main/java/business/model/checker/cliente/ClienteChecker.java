package business.model.checker.cliente;


import java.time.LocalDate;

import javafx.scene.control.DatePicker;
import business.entity.Cliente;
import business.entity.Entity;
import business.model.Exception.CommonException;
import business.model.checker.Checker;

public class ClienteChecker implements Checker{
	
    private static final int MIN_NOME_VALUE = 3;
    private static final int MAX_NOME_VALUE = 20;
    
    private static final int MIN_COGNOME_VALUE = 3;
    private static final int MAX_COGNOME_VALUE = 20;
    
    private static final int MAX_SESSO_VALUE=7;
    
    protected static final int MIN_INDIRIZZO_VALUE=10;
    protected static final int MAX_INDIRIZZO_VALUE=50;
    
    private static final int CODFISCALE_VALUE= 16;

    protected static final int MIN_PARTITAIVA_VALUE=0;
    protected static final int MAX_PARTITAIVA_VALUE=11;
    
    protected static final int MIN_EMAIL_VALUE=5;
    protected static final int MAX_EMAIL_VALUE=20;
    
    protected static final int NUMCELL_VALUE= 10;
    protected static final int MIN_NUM_TEL_VALUE=0;
    protected static final int MAX_NUMTEL_VALUE= 10;

    private static final int NUMPATENTE_VALUE= 10;
    
    private static final DatePicker date = new DatePicker(LocalDate.of(1905,1, 1));
    private static final LocalDate dateLimit= date.getValue();
    protected Cliente cliente;
    protected boolean isValid;
    
    
    
	@Override
	public void check(Entity entity) throws CommonException {
		cliente= (Cliente) entity;
        checkNome();
        checkCognome();
        checkSesso();
        checkDataNascita();
        checkDataEmissPatente();
        checkDataScadPatente();
        checkCodFiscale();
        checkNumCell();
        checkNumTel();
        checkPatente();
        checkPartitaIva();
	}
	
	private void checkDataNascita() throws CommonException {
		if(cliente.getDatanascita().isBefore(dateLimit)){
        	throw new CommonException("Data di nascita non valida");
		}
	}
	
	private void checkDataEmissPatente() throws CommonException {
		LocalDate datee = cliente.getDatanascita();
		DatePicker datepicker= new DatePicker(LocalDate.of(datee.getYear()+18, datee.getMonth(),datee.getDayOfMonth()));
		datee= datepicker.getValue();
		if(cliente.getDataEmissPatente()==null || cliente.getDataEmissPatente().isBefore(datee)){
        	throw new CommonException("Data emissione patente non valida");
		}
	}
	
	private void checkDataScadPatente() throws CommonException {
		if(cliente.getDataEmissPatente()==null || cliente.getDataScadPatente().isBefore(LocalDate.now())){
        	throw new CommonException("Patente Scaduta");
		}
	}

	
	private void checkNome() throws CommonException{
		int length;
		
        length = cliente.getNome().length();

        isValid = (length >= MIN_NOME_VALUE)
                && (length <= MAX_NOME_VALUE);
        
        if (!isValid) {
        	throw new CommonException("Nome  non valido");
        }
	}
	
	
	private void checkCognome() throws CommonException{
		int length;
		
        length = cliente.getCognome().length();

        isValid = (length >= MIN_COGNOME_VALUE)
                && (length <= MAX_COGNOME_VALUE);

        if (!isValid) {
        	throw new CommonException("Cognome  non valido");
        }
	}
	
	private void checkSesso() throws CommonException{
		int length;
		
        length = cliente.getSesso().length();

        isValid =  (length == MAX_SESSO_VALUE);

        if (!isValid) {
        	throw new CommonException("Sesso non valido");
        }
	}

	
	private void checkCodFiscale() throws CommonException{
		int length;
		
        length = cliente.getCodFiscale().length();

        isValid = (length==CODFISCALE_VALUE);

        if (!isValid) {
        	throw new CommonException("Codice Fiscale non valido");
        }
	}
	

	private void checkPartitaIva() throws CommonException{
		int length;
		
        length = cliente.getPartitaIva().length();

        isValid = (length==MAX_PARTITAIVA_VALUE) || (length== MIN_PARTITAIVA_VALUE);

        if (!isValid) {
        	throw new CommonException("Partita iva non valido");
        }
	}
	


	private void checkNumCell() throws CommonException{
		int length;
		
        length = cliente.getNumCell().length();

        isValid = (length==NUMCELL_VALUE);

        if (!isValid) {
        	throw new CommonException("Numero cellulare non valido");
        }
	}

	private void checkNumTel() throws CommonException{
		int length;
		
        length = cliente.getNumTel().length();

        isValid = (length==MAX_NUMTEL_VALUE) || (length==MIN_NUM_TEL_VALUE);

        if (!isValid) {
        	throw new CommonException("Numero telefono non valido");
        }
	}

	private void checkPatente() throws CommonException{
		int length;
		
        length = cliente.getPatenteGuida().length();

        isValid = (length==NUMPATENTE_VALUE);

        if (!isValid) {
        	throw new CommonException("Patente non valida");
        }
	}
}
