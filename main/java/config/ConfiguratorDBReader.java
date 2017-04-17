package config;

/**
 * Legge i valori inerenti la gestione del database dal file di configurazione.
 * 
 * @author Gianluca
 * 
 */
public class ConfiguratorDBReader implements ConfiguratorReader {

	public static final String PORT="port";

	private GestioneProperties gp;

	/**
	 * Parametro username.
	 */
	public static final String USERNAME = "usernameDB";

	/**
	 * Parametro password.
	 */
	public static final String PASSWORD = "passwordDB";

	/**
	 * Parametro Host.
	 */
	public static final String HOST = "hostDB";

	/**
	 * Parametro database.
	 */
	public static final String DATABASE = "dataBaseDB";

	/**
	 * Costruttore principale.
	 * 
	 * @param path
	 *            il percorso del file da cui leggere i valori.
	 */
	public ConfiguratorDBReader(String path) {
		this.gp = new GestioneProperties(path);
	}

	@Override
	public String getField(String key) {
		// TODO Auto-generated method stub
		return gp.getValue(key);
	}

}
