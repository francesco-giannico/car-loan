package integration.DAO;

/**
 * <p>Eccezione sollevata in caso di fallimento della connessione al Database</p>
 */
@SuppressWarnings("serial")
public class DatabaseConnectionException extends Exception {
	/** Costruttore */
	public DatabaseConnectionException(String description){
		super(description);
	}
}
